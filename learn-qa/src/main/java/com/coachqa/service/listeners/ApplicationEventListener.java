package com.coachqa.service.listeners;

import notification.entity.ApplicationEvent;

/**
 *
 */
public interface ApplicationEventListener {

    void onEvent(ApplicationEvent event);

    default String getName() {
        return this.getClass().getName();
    }

}
