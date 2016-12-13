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
    private final SimpleEventPublisher publisher;

//    @Autowired
//    private PostService postService;

    public ContentApprovalListener(SimpleEventPublisher publisher) {
        this.publisher =  publisher;
    }

    @Override
    public void onEvent(ApplicationEvent event) {

        processEvent(event);
//        event.setStage(EventStage.STAGE_TWO);
//        publisher.publishEvent(event);

    }

    private void processEvent(ApplicationEvent event) {
//        postService.updateApprovalStatus();
    }
}
