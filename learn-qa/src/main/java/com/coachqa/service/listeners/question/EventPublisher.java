package com.coachqa.service.listeners.question;

import com.coachqa.service.listeners.ApplicationEventListener;
import notification.entity.ApplicationEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Queue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class EventPublisher implements ApplicationEventListener<Integer> {

    private static final Logger LOGGER = LoggerFactory.getLogger(EventPublisher.class);


    private List<ApplicationEventListener<Integer>> listeners ;

    private BlockingQueue<ApplicationEvent<Integer>> questionUpdatesQueue =  new LinkedBlockingQueue<>();

    public EventPublisher(List<ApplicationEventListener<Integer>> eventListeners){
        listeners =  eventListeners;
        new Thread("question-event-publisher"){
            @Override
            public void run() {
                startInvokingListeners(listeners);
            }
        }.start();

    }

    private void startInvokingListeners(List<ApplicationEventListener<Integer>> listeners)  {

        while(true){
            ApplicationEvent event;
            try {
                event = questionUpdatesQueue.take();
            } catch (InterruptedException e) {
                LOGGER.warn("Queue interrupted", e);
                continue;
            }

            for (ApplicationEventListener l : listeners){
                invokeUpdateListener(l, event);
            }
        }
    }


    @Override
    public void onEvent(ApplicationEvent<Integer> event) {
        Queue<ApplicationEvent<Integer>> queue = questionUpdatesQueue;
        queue.offer(event);
    }

    private void invokeUpdateListener(ApplicationEventListener<Integer> questionPostListener, ApplicationEvent<Integer> questionId) {
        try{
            questionPostListener.onEvent(questionId);
        }catch (Throwable throwable){
            LOGGER.error("Unexpected error occurred in trying to invoke listener : "+ questionPostListener.getClass().getName(), throwable);
        }
    }


}
