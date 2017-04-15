package com.coachqa.ws.controllor;

import com.coachqa.entity.AppUser;
import com.coachqa.service.UserService;
import com.coachqa.ws.util.WSUtil;
import notification.NotificationService;
import notification.entity.ApplicationEvent;
import notification.entity.UserEventNotification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by a.nigam on 13/02/16.
 */
@RestController("/api/notification")
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    @Autowired
    private UserService userService;

    @RequestMapping(path="/register", method = RequestMethod.POST)
    public void registerForEvent(int userId, ApplicationEvent event) {
        notificationService.registerForEvent(userId, event);
    }

    @RequestMapping(path="/deregister", method = RequestMethod.POST)
    public void DeRegisterForEvent(int userId, ApplicationEvent event) {
        notificationService.deRegisterForEvent(userId, event);
    }

    @RequestMapping(path="/acknowledge", method = RequestMethod.POST)
    public void notificationAcknowledged(int userId, ApplicationEvent event) {
        // notificationService.updateNotificationStatus(userId, event);
    }

    @RequestMapping(path="/user", method = RequestMethod.POST)
    public List<UserEventNotification> getUserNotifications() {
        AppUser user = WSUtil.getUser(userService);

        List<UserEventNotification> notifications = notificationService.getUserNotifications(user.getAppUserId());

        return notifications;
    }

}
