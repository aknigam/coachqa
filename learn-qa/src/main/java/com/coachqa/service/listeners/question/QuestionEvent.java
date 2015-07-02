package com.coachqa.service.listeners.question;


import com.coachqa.service.listeners.ApplicationEventTypeEnum;
import com.coachqa.service.listeners.BaseEvent;

public class QuestionEvent extends BaseEvent {

    public Integer getQuestionId() {
        return questionId;
    }

    private Integer questionId;

    public QuestionEvent(Integer questionId, ApplicationEventTypeEnum applicationEventTypeEnum){
        super(applicationEventTypeEnum);
        this.questionId = questionId;

    }


}
