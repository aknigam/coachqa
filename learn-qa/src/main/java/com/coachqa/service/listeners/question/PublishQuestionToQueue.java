package com.coachqa.service.listeners.question;

import com.coachqa.service.listeners.ApplicationEventListener;
import com.coachqa.service.listeners.ApplicationEventTypeEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class PublishQuestionToQueue  implements ApplicationEventListener<QuestionEvent> {

    private static final Logger LOGGER = LoggerFactory.getLogger(PublishQuestionToQueue.class);
    private final Map<ApplicationEventTypeEnum, Queue<QuestionEvent>> eventQueues;

    private List<QuestionPostListener> listeners ;

    private BlockingQueue<QuestionEvent> questionUpdatesQueue =  new LinkedBlockingQueue<QuestionEvent>();

    public PublishQuestionToQueue(){

        Map<ApplicationEventTypeEnum, Queue<QuestionEvent>> eventQueues = new HashMap<>();

        eventQueues.put(ApplicationEventTypeEnum.QUESTION_ANSWERED,  new LinkedBlockingQueue<QuestionEvent>());
        eventQueues.put(ApplicationEventTypeEnum.QUESTION_POSTED,  new LinkedBlockingQueue<QuestionEvent>());
        eventQueues.put(ApplicationEventTypeEnum.QUESTION_UPDATED,  new LinkedBlockingQueue<QuestionEvent>());
        this.eventQueues = eventQueues;

        listeners =  new ArrayList<>();
        listeners.add(new EmailNotificationQuestionListener());
        listeners.add(new ImageToTextQuestionConverterQuestionListener());
        listeners.add(new IndexQuestionListener());

        new Thread("question-event-publisher"){
            @Override
            public void run() {
                startInvokingListeners(listeners);
            }
        }.start();

    }

    private void startInvokingListeners(List<QuestionPostListener> listeners)  {

        while(true){
            QuestionEvent event = null;
            try {
                event = questionUpdatesQueue.take();
            } catch (InterruptedException e) {
                LOGGER.warn("Queue interrupted", e);
                continue;
            }

            for (QuestionPostListener l : listeners){
                invokeUpdateListener(l, event);
            }
        }
    }


    @Override
    public void onEvent(QuestionEvent event) {
        Queue<QuestionEvent> queue = questionUpdatesQueue;
        queue.offer(event);
    }

    private void invokeUpdateListener(QuestionPostListener questionPostListener, QuestionEvent questionId) {
        try{
            questionPostListener.onEvent(questionId);
        }catch (Throwable throwable){
            LOGGER.error("Unexpected error occurred in trying to invoke listener : "+ questionPostListener.getClass().getName(), throwable);
        }
    }


}
