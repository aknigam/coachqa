package com.coachqa.service.listeners;


public class BaseEvent {

    private ApplicationEventTypeEnum applicationEvent;

    public BaseEvent(ApplicationEventTypeEnum applicationEventTypeEnum) {
        this.applicationEvent = applicationEventTypeEnum;
    }

    public ApplicationEventTypeEnum getApplicationEvent() {
        return applicationEvent;
    }


}
