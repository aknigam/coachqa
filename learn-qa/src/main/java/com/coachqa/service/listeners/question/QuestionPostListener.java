package com.coachqa.service.listeners.question;


import com.coachqa.service.listeners.ApplicationEventListener;
import notification.entity.ApplicationEvent;
import notification.entity.EventStage;
import notification.entity.EventType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public abstract class QuestionPostListener implements  ApplicationEventListener<Integer> {

    private static final Logger LOGGER = LoggerFactory.getLogger(QuestionPostListener.class);

    @Override
    public void onEvent(ApplicationEvent<Integer> event) {
        if(event.getStage() == EventStage.STAGE_ONE){
            System.out.println("Post not yet approved");
            return;
        }
        EventType eventType = event.getEventType();
        if(eventType == EventType.QUESTION_POSTED){
            questionPosted(event.getEventSource());
        }
        else if(eventType == EventType.QUESTION_UPDATED){
            questionUpdated(event.getEventSource());
        } else if(eventType == EventType.QUESTION_ANSWERED){
            questionAnswered(event.getEventSource());
        }
        else{
            LOGGER.warn("Event of unknown event type sent to question change listener :"+ eventType);
        }
    }

    public abstract void questionPosted(Integer questionId);

    public abstract void questionUpdated(Integer questionId);

    public abstract void questionAnswered(Integer questionId);



}
