package com.coachqa.service.impl;

import com.coachqa.entity.AppUser;
import com.coachqa.exception.ApplicationErrorCode;
import com.coachqa.exception.InvalidEventForApprovalException;
import com.coachqa.exception.NotAuthorisedToApproveException;
import com.coachqa.service.PostService;
import com.coachqa.ws.model.PostApproval;
import notification.entity.ApplicationEvent;
import notification.entity.EventStage;
import notification.entity.EventType;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by a.nigam on 08/05/17.
 */
public class PostApprovalProcessor extends ApprovalService {


    @Autowired
    private PostService postService;


    protected void validateApprover(ApplicationEvent<Integer> event, AppUser approver) throws NotAuthorisedToApproveException{
        EventType eventType = event.getEventType();
        if(eventType == EventType.QUESTION_ANSWERED || eventType == EventType.QUESTION_POSTED
                || eventType == EventType.QUESTION_UPDATED ){
            // Content approval required - approver has to be admin
            // todo remove hard coding
            if(approver.getAppUserId() != 8){
                throw new NotAuthorisedToApproveException(ApplicationErrorCode.NOT_AUTHORIZEDTO_APPROVE, "Only admin can approve the request");
            }
            return;


        }
        throw new InvalidEventForApprovalException(ApplicationErrorCode.IN_VALID_EVENT, "Invalid event type");

    }

    protected ApplicationEvent<Integer> processApproval(ApplicationEvent<Integer> event, boolean isRequestApproved, AppUser approver) {
        PostApproval postApproval = new PostApproval();
        postApproval.setApproved(true);
        postApproval.setApprovedBy(approver.getAppUserId());
        postApproval.setPostId(event.getEventSource());
        postService.updateApprovalStatus(postApproval);
        event.setStage(EventStage.STAGE_TWO);
        return event;
    }

}
