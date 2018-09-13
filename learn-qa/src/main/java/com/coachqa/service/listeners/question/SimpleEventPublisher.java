package com.coachqa.service.listeners.question;

import com.coachqa.service.listeners.ApplicationEventListener;
import com.coachqa.util.CollectionUtils;
import notification.entity.ApplicationEvent;
import notification.entity.EventType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
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
            ApplicationEvent event;
            try {
                event = questionUpdatesQueue.take();
            } catch (InterruptedException e) {
                LOGGER.warn("Queue interrupted", e);
                continue;
            }

            List<ApplicationEventListener<E>> listeners = eventListeners.get(event.getEventType());

            for (ApplicationEventListener l : listeners) {
                LOGGER.debug("Invoked listener: ["+l.getClass().getName()+"]");
                invokeUpdateListener(l, event);
            }


        }
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

}
