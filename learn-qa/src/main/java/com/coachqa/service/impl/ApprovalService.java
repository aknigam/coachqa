package com.coachqa.service.impl;

import com.coachqa.entity.AppUser;
import com.coachqa.exception.NotAuthorisedToApproveException;
import com.coachqa.service.ApprovalProcessor;
import com.coachqa.service.listeners.question.EventPublisher;
import notification.entity.ApplicationEvent;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by a.nigam on 21/04/17.
 */
public abstract class ApprovalService implements ApprovalProcessor {


    @Autowired
    private EventPublisher eventPublisher;

    /**
     * How to the  handle scenario which require multi-step approval.
     * E.g 1st the content gets approved and then the classowner approves the post.
     *
     * @param event
     * @param approver
     * @param isRequestApproved
     * @throws NotAuthorisedToApproveException
     */
    @Override
    public void processApprovalRequest(ApplicationEvent<Integer> event, AppUser approver, boolean isRequestApproved) throws NotAuthorisedToApproveException {

        validateApprover(event, approver);

        raiseApplicationEvent(processApproval(event, isRequestApproved, approver));


    }

    protected abstract void validateApprover(ApplicationEvent<Integer> event, AppUser approver) throws NotAuthorisedToApproveException;


    protected abstract ApplicationEvent<Integer> processApproval(ApplicationEvent<Integer> event, boolean isRequestApproved, AppUser approver) ;


    private void raiseApplicationEvent(ApplicationEvent applicationEvent) {
        // phase 2 event should be generated from here
        eventPublisher.publishEvent(applicationEvent);
    }
}
