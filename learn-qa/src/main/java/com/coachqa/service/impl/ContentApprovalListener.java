package com.coachqa.service.impl;

import com.coachqa.service.PostService;
import com.coachqa.service.listeners.ApplicationEventListener;
import com.coachqa.service.listeners.question.SimpleEventPublisher;
import notification.entity.ApplicationEvent;
import notification.entity.EventStage;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by a.nigam on 13/12/16.
 */
public class ContentApprovalListener implements ApplicationEventListener {



//    @Autowired
//    private PostService postService;

    public ContentApprovalListener() {

    }

    /**
     * This will just notify the admin through email or some other mechanism.
     * On seeing the notification the admin will approve/reject the post.
     *
     * @param event
     */
    @Override
    public void onEvent(ApplicationEvent event) {
        processEvent(event);

    }
    private void processEvent(ApplicationEvent event) {
//        postService.updateApprovalStatus();
    }
}
