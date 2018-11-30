package com.coachqa.ws.controllor;

import com.coachqa.entity.AppUser;
import com.coachqa.service.UserService;
import com.coachqa.ws.util.WSUtil;
import notification.NotificationService;
import notification.entity.UserEventNotification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/alert")
public class AlertsControllor {

    @Autowired
    private UserService userService;

    @Autowired
    private NotificationService notificationService;

    /**
     * Find all the events and then get registered users
     * Should get this information from the UEN
     */
    @GetMapping
    public List<UserEventNotification> alerts(){
        AppUser user = WSUtil.getUser(userService);
        // this will have info about event source and the event type. based on this one has to show the content
        return notificationService.getUserNotifications(user.getAppUserId());
    }


}
