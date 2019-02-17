package com.coachqa.ws.controllor;

import com.coachqa.entity.AppUser;
import com.coachqa.service.ClassroomService;
import com.coachqa.service.PostService;
import com.coachqa.service.UserService;
import com.coachqa.ws.util.WSUtil;
import notification.NotificationService;
import notification.entity.UserEventNotification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/alert")
public class AlertsControllor {

    @Autowired
    private UserService userService;

    @Autowired
    private PostService postService;

    @Autowired
    private ClassroomService classroomService;

    @Autowired
    private NotificationService notificationService;




}
