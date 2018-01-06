package com.coachqa.service.impl;

import com.coachqa.entity.AppUser;
import com.coachqa.entity.Classroom;
import com.coachqa.exception.ApplicationErrorCode;
import com.coachqa.exception.InvalidEventForApprovalException;
import com.coachqa.exception.NotAuthorisedToApproveException;
import com.coachqa.service.ClassroomService;
import com.coachqa.service.PostService;
import com.coachqa.service.listeners.question.EventPublisher;
import com.coachqa.ws.model.PostApproval;
import notification.entity.ApplicationEvent;
import notification.entity.EventStage;
import notification.entity.EventType;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Date;

/**
 * Created by a.nigam on 08/05/17.
 */
public class ClassroomEventApprovalProcessor extends ApprovalService {


    @Autowired
    private ClassroomService classroomService;


    protected void validateApprover(ApplicationEvent<Integer> event, AppUser approver) throws NotAuthorisedToApproveException{

        Integer classroomId = event.getEventSource();
        Classroom classroom = classroomService.getClassroom(classroomId);
        if(!classroom.getClassOwner().getAppUserId().equals(approver.getAppUserId())) {
            throw new NotAuthorisedToApproveException(ApplicationErrorCode.NOT_AUTHORIZEDTO_APPROVE, "Only admin can approve the request");
        }

//        throw new InvalidEventForApprovalException(ApplicationErrorCode.IN_VALID_EVENT, "Invalid event type");

    }

    protected ApplicationEvent<Integer> processApproval(ApplicationEvent<Integer> event, boolean isRequestApproved, AppUser approver) {

        classroomService.approveMembershipRequest(event.getEventSource(), event.getEventRaisedByEntityId());

        // the new event should be a notification to the person who requested membership
        ApplicationEvent<Integer> approvedEvent = new ApplicationEvent<Integer>(EventType.MEMBERSHIP_APPROVED,
                event.getEventSource(), event.getEventRaisedByEntityId(),
                new Date(System.currentTimeMillis()),
                new Date(System.currentTimeMillis()));
        approvedEvent.setStage(EventStage.STAGE_ONE);
        return approvedEvent;
    }

}
