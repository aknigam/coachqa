package com.coachqa.service;

import com.coachqa.entity.AppUser;
import com.coachqa.exception.NotAuthorisedToApproveException;
import notification.entity.ApplicationEvent;

/**
 * Created by a.nigam on 21/04/17.
 */
public interface ApprovalProcessor {


    void processApprovalRequest(ApplicationEvent<Integer> event, AppUser approver, boolean isRequestApproved) throws NotAuthorisedToApproveException;

}
