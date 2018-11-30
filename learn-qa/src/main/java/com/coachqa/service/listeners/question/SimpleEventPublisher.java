package com.coachqa.service.listeners.question;

import com.coachqa.service.impl.UsersNotificationListener;
import com.coachqa.service.listeners.ApplicationEventListener;
import com.coachqa.service.listeners.SimpleRetryingEventListener;
import com.coachqa.util.CollectionUtils;
import notification.entity.ApplicationEvent;
import notification.entity.EventType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class SimpleEventPublisher<E> implements EventPublisher<E> {

    private static final Logger LOGGER = LoggerFactory.getLogger(SimpleEventPublisher.class);


//    private ApplicationEventListener<E> stageOneListener;
    private Map<EventType, List<ApplicationEventListener<E>>> eventListeners = new HashMap<>();

    private ApplicationEventListener defaultListener;

    private BlockingQueue<ApplicationEvent<E>> questionUpdatesQueue =  new LinkedBlockingQueue<>();

    public SimpleEventPublisher(){

        new Thread("question-event-publisher"){
            @Override
            public void run() {
                startInvokingListeners();
            }
        }.start();
    }

    private void startInvokingListeners()  {

        while(true){
            try{

                ApplicationEvent event;
                try {
                    event = questionUpdatesQueue.take();
                } catch (InterruptedException e) {
                    LOGGER.warn("Queue interrupted", e);
                    continue;
                }

                List<ApplicationEventListener<E>> listeners = getEvenListeners(event);

                for (ApplicationEventListener l : listeners) {
                    LOGGER.debug("Invoked listener: ["+l.getClass().getName()+"]");
                    invokeUpdateListener(l, event);
                }

            }
            catch (Throwable t) {
                t.printStackTrace();
            }


        }

    }

    private List<ApplicationEventListener<E>> getEvenListeners(ApplicationEvent event) {
        List<ApplicationEventListener<E>> l = eventListeners.get(event.getEventType());
        if(CollectionUtils.isEmpty(l)) {
            return Collections.singletonList(defaultListener);
        }
        return l;

    }


    public void publishEvent(ApplicationEvent<E> event) {
        Queue<ApplicationEvent<E>> queue = questionUpdatesQueue;
        queue.offer(event);
    }

    @Override
    public void attachListener(EventType eventType, ApplicationEventListener listener) {
        List<ApplicationEventListener<E>> listeners = eventListeners.get(eventType);
        if(CollectionUtils.isEmpty(listeners)){
            listeners = new ArrayList<>();
            eventListeners.put(eventType, listeners);
        }

        listeners.add(listener);
    }

    private void invokeUpdateListener(ApplicationEventListener<E> questionPostListener, ApplicationEvent<E> event) {
        try{
            questionPostListener.onEvent(event);
        }catch (Throwable throwable){
            LOGGER.error("Unexpected error occurred in trying to invoke listener : "+ questionPostListener.getClass().getName(), throwable);
        }
    }

    public void setDefaultListener(ApplicationEventListener defaultListener) {
        this.defaultListener = defaultListener;
    }
}
