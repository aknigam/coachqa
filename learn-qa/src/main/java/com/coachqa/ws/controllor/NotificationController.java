package com.coachqa.ws.controllor;

import notification.NotificationService;
import notification.entity.ApplicationEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by a.nigam on 13/02/16.
 */
@RestController("/api/notification")
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    @RequestMapping(value="/register", method = RequestMethod.POST)
    public void registerForEvent(int userId, ApplicationEvent event) {
        notificationService.registerForEvent(userId, event);
    }

    @RequestMapping(value="/deregister", method = RequestMethod.POST)
    public void DeRegisterForEvent(int userId, ApplicationEvent event) {
        notificationService.deRegisterForEvent(userId, event);
    }

    @RequestMapping(value="/acknowledge", method = RequestMethod.POST)
    public void notificationAcknowledged(int userId, ApplicationEvent event) {
        // notificationService.updateNotificationStatus(userId, event);
    }

}
