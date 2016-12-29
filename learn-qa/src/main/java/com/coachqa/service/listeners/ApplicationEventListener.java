package com.coachqa.service.listeners;

import com.coachqa.service.listeners.question.SimpleEventPublisher;
import notification.entity.ApplicationEvent;

/**
 *
 */
public interface ApplicationEventListener<E> {

    void onEvent(ApplicationEvent<E> event);

}
