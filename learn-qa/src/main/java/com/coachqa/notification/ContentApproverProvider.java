package com.coachqa.notification;


import com.coachqa.service.UserService;
import notification.EventRegisteredUsersProvider;
import notification.entity.ApplicationEvent;
import notification.entity.EventType;
import notification.repository.EventRegistrationDao;
import org.springframework.util.Assert;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class ContentApproverProvider implements EventRegisteredUsersProvider {

    private final UserService userService;

    public ContentApproverProvider(UserService userService) {
        Assert.notNull(userService, "Userservice service must not be null to init "+ ContentApproverProvider.class.getName());
        this.userService  = userService;
    }

    @Override
    public List<Integer> getUsersRegisteredByDefault(ApplicationEvent<Integer> event, EventRegistrationDao eventRegistrationDao) {
        if(event.getEventType() == EventType.ANSWER_POSTED
                || event.getEventType() == EventType.QUESTION_POSTED) {

            return userService.getPostContentApprovers();
        }
        return Collections.emptyList();
    }
}
