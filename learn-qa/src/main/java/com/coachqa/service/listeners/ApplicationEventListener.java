package com.coachqa.service.listeners;

import notification.entity.ApplicationEvent;

/**
 *
 */
public interface ApplicationEventListener<E> {

    void onEvent(ApplicationEvent<E> event);

}
