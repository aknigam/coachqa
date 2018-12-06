package com.coachqa.service.listeners.question;

import com.coachqa.service.listeners.ApplicationEventListener;
import com.coachqa.util.CollectionUtils;
import notification.entity.ApplicationEvent;
import notification.entity.EventType;
import notification.repository.EventDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class SimpleEventConsumer implements EventConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(SimpleEventConsumer.class);
    private static final int MAX_RETRIALS = 3;

    @Autowired
    private EventDAO eventDAO;

//    private ApplicationEventListener stageOneListener;
    private Map<EventType, List<ApplicationEventListener>> eventListeners = new HashMap<>();

    private ApplicationEventListener defaultListener;

    private BlockingQueue questionUpdatesQueue =  new LinkedBlockingQueue<>();
    private final UUID consumerUniquekey;

    private SimpleEventPublisher eventPublisher;

    public SimpleEventConsumer(BlockingQueue queue){
        this.questionUpdatesQueue = queue;
        consumerUniquekey = UUID.randomUUID();

    }

    @PostConstruct
    public void init() {
        new Thread("question-event-publisher"){
            @Override
            public void run() {
                startInvokingListeners();
            }
        }.start();
    }


    private List<ApplicationEvent> getPendingEvents() {
        return Collections.emptyList();
    }

    private void startInvokingListeners()  {

        while(true){
            try{

                ApplicationEvent event;
                try {
//                    this is consumer side of the work
                    event = (ApplicationEvent) questionUpdatesQueue.take();
                } catch (InterruptedException e) {
                    LOGGER.warn("Queue interrupted", e);
                    continue;
                }

                List<ApplicationEventListener> listeners = getEvenListeners(event);

                boolean listenerInvocationSuccessful = true;
                for (ApplicationEventListener l : listeners) {
                    LOGGER.debug("Invoked listener: ["+l.getClass().getName()+"]");
                    listenerInvocationSuccessful = invokeUpdateListener(l, event);
                }
                // finally
                if(listenerInvocationSuccessful) {
                    eventDAO.deleteEvent(event.getId());
                    // it can republish with incremental delay here
                    int noOfretries = event.getRetryCount();
                    if(noOfretries <= MAX_RETRIALS) {
                        event.setRetryCount(noOfretries + 1);
//                        eventPublisher.publishEvent(event);
                    }

                }


                /*
                Delete the event here if all the listeners return succesfully. This should have a cascading effect
                If any of the listener fails then mark it as failed and set the event retrial attempt so as to know
                that event is being retried
                In case of retry it should look at the history ot listener marked as failed and invoke them
                 */

            }
            catch (Throwable t) {
                LOGGER.error("Unexpected error occurred while consuming event", t);
            }


        }

    }

    private boolean invokeUpdateListener(ApplicationEventListener questionPostListener, ApplicationEvent event) {
        try{
            // TODO: 03/12/18 event creation should happen here. NOT EVEN here becuase this mthod
            // is invoked multiple times for each event
            // use event service to create event
            questionPostListener.onEvent(event);
            return true;
            // TODO: 03/12/18 delete event and all the UENs
            // use event service to delete the event
        }catch (Throwable throwable){
            LOGGER.error("Unexpected error occurred in trying to invoke listener : "+ questionPostListener.getClass().getName(), throwable);
//            eventDAO.markListenerInvocationUnsucessful(event.getId(), questionPostListener.getName(),
//                    consumerUniquekey);
            return false;
            // mark it as unsuccessfull. It will be used in case of retrials
        }

    }

    private List<ApplicationEventListener> getEvenListeners(ApplicationEvent event) {

        // this should check event retrial status
        // and in case of retrial it should invoke only those listeners which failed last time.


        List<ApplicationEventListener> l = eventListeners.get(event.getEventType());
        if(CollectionUtils.isEmpty(l)) {
            return Collections.singletonList(defaultListener);
        }
        return l;

    }


    @Override
    public void attachListener(EventType eventType, ApplicationEventListener listener) {
        List<ApplicationEventListener> listeners = eventListeners.get(eventType);
        if(CollectionUtils.isEmpty(listeners)){
            listeners = new ArrayList<>();
            eventListeners.put(eventType, listeners);
        }

        listeners.add(listener);
    }



    public void setDefaultListener(ApplicationEventListener defaultListener) {
        this.defaultListener = defaultListener;
    }
}
