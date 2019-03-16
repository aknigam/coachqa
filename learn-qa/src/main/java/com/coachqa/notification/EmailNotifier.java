package com.coachqa.notification;

import notification.EventNotifier;
import notification.NotifierFactory;
import notification.enums.NotificationTypeEnum;
import notification.impl.UserDetailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

@Component
public class EmailNotifier implements EventNotifier {

    private Logger LOGGER = LoggerFactory.getLogger(EmailNotifier.class);

    private final UserDetailService userDetailService;

    @Autowired
    private NotifierFactory notifierFactory;

    @PostConstruct
    public void init (){
        notifierFactory.register(NotificationTypeEnum.EMAIL, this);
    }

    public EmailNotifier(UserDetailService userDetailService) {
        this.userDetailService = userDetailService;
    }

    @Override
    public void sendNotification(String notificationMessage, int userId) {
        LOGGER.info("Sending android app notification {} to user {}", notificationMessage, userId);

        List<String> emails = userDetailService.getEmailAddresses(userId);



    }
}
