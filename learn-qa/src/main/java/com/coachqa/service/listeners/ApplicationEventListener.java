package com.coachqa.service.listeners;

/**
 * Created by anigam on 6/29/15.
 */
public interface ApplicationEventListener<T extends  BaseEvent> {

    void onEvent(T event);


}
