package com.coachqa.service.listeners.question;


import com.coachqa.service.listeners.ApplicationEventListener;
import com.coachqa.service.listeners.ApplicationEventTypeEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public abstract class QuestionPostListener implements ApplicationEventListener<QuestionEvent> {

    private static final Logger LOGGER = LoggerFactory.getLogger(QuestionPostListener.class);
    @Override
    public void onEvent(QuestionEvent event) {
        ApplicationEventTypeEnum eventType = event.getApplicationEvent();
        if(eventType == ApplicationEventTypeEnum.QUESTION_POSTED){
            questionPosted(event.getQuestionId());
        }
        else if(eventType == ApplicationEventTypeEnum.QUESTION_UPDATED){
            questionUpdated(event.getQuestionId());
        } else if(eventType == ApplicationEventTypeEnum.QUESTION_ANSWERED){
            questionAnswered(event.getQuestionId());
        }
        else{
            LOGGER.warn("Event of unknown event type sent to question change listener :"+ eventType);
        }
    }

    public abstract void questionPosted(Integer questionId);

    public abstract void questionUpdated(Integer questionId);

    public abstract void questionAnswered(Integer questionId);



}
