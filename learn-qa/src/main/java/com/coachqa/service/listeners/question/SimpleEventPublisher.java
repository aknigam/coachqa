package com.coachqa.service.listeners.question;

import com.coachqa.service.listeners.ApplicationEventListener;
import notification.entity.ApplicationEvent;
import notification.entity.EventStage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Queue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class SimpleEventPublisher<E> implements EventPublisher<E> {

    private static final Logger LOGGER = LoggerFactory.getLogger(SimpleEventPublisher.class);


    private ApplicationEventListener<E> stageOneListener;
    private List<ApplicationEventListener<E>> listeners ;

    private BlockingQueue<ApplicationEvent<E>> questionUpdatesQueue =  new LinkedBlockingQueue<>();

    public SimpleEventPublisher(List<ApplicationEventListener<E>> eventListeners){
        listeners =  eventListeners;
        new Thread("question-event-publisher"){
            @Override
            public void run() {
                startInvokingListeners(listeners);
            }
        }.start();
    }

    private void startInvokingListeners(List<ApplicationEventListener<E>> listeners)  {

        while(true){
            ApplicationEvent event;
            try {
                event = questionUpdatesQueue.take();
            } catch (InterruptedException e) {
                LOGGER.warn("Queue interrupted", e);
                continue;
            }
            EventStage stage= event.getStage();
            switch (stage  ) {
                case STAGE_ONE:
                    stageOneListener.onEvent(event);
                    break;
                case STAGE_TWO:
                    for (ApplicationEventListener l : listeners){
                        invokeUpdateListener(l, event);
                    }
                    break;
            }

        }
    }



    public void publishEvent(ApplicationEvent<E> event) {
        Queue<ApplicationEvent<E>> queue = questionUpdatesQueue;
        queue.offer(event);
    }

    private void invokeUpdateListener(ApplicationEventListener<E> questionPostListener, ApplicationEvent<E> event) {
        try{
            questionPostListener.onEvent(event);
        }catch (Throwable throwable){
            LOGGER.error("Unexpected error occurred in trying to invoke listener : "+ questionPostListener.getClass().getName(), throwable);
        }
    }


    public void setStageOneListener(ApplicationEventListener stageOneListener) {
        this.stageOneListener = stageOneListener;
    }
}
