package com.coachqa.service.listeners.question;

import com.coachqa.service.listeners.ApplicationEventListener;
import notification.NotificationService;
import notification.entity.ApplicationEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Anand on 10/19/2015.
 */
public class Notificationlistener implements ApplicationEventListener<Integer> {

    private static final Logger LOGGER = LoggerFactory.getLogger(Notificationlistener.class);

    private NotificationService notificationService;

    public Notificationlistener(NotificationService notificationService){
        this.notificationService = notificationService;
    }
    @Override
    public void onEvent(ApplicationEvent<Integer> event) {
        LOGGER.debug("Notification module invoked for event: "+ event);
        notificationService.notifyUsers(event);
    }
}
