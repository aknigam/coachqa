package com.coachqa.service.impl;

import com.coachqa.service.listeners.ApplicationEventListener;
import notification.NotificationService;
import notification.entity.ApplicationEvent;
import org.springframework.util.Assert;

/**
 * All the events which require user action through notification should be handled by this listener.
 * These events and corresponding actions are as follows:
 *
 * 1. Question/answer posted - notification should go to admin for approval
 * 2. Post approved - notification should go to person who posted it + all the other who are interested in this event directly
 * or indirectly.
 *
 * Its the responsibility of notification service to generate appropriate message according to the context(event type
 * etc) and send the notification accordingly.
 *
 */
public class UsersNotificationListener implements ApplicationEventListener {


    private NotificationService notificationService;

    public UsersNotificationListener(NotificationService notificationService){
        this.notificationService = notificationService;
        Assert.notNull(notificationService, "Notification service required for User notification listener");
    }

    @Override
    public void onEvent(ApplicationEvent event) {
        notificationService.notifyUsers(event);
    }
}
