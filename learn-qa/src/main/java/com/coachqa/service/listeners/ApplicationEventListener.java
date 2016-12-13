package com.coachqa.service.listeners;

import com.coachqa.service.listeners.question.SimpleEventPublisher;
import notification.entity.ApplicationEvent;

/**
 * Created by anigam on 6/29/15.
 */
public interface ApplicationEventListener<E> {

    void onEvent(ApplicationEvent<E> event);



}
