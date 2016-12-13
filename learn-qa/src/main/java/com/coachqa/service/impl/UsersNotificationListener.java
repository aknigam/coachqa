package com.coachqa.service.impl;

import com.coachqa.service.listeners.ApplicationEventListener;
import notification.NotificationService;
import notification.entity.ApplicationEvent;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by a.nigam on 13/12/16.
 */
public class UsersNotificationListener implements ApplicationEventListener<Integer> {

    @Autowired
    private NotificationService notificationService;

    @Override
    public void onEvent(ApplicationEvent<Integer> event) {
        notificationService.notifyUsers(event);
    }
}
