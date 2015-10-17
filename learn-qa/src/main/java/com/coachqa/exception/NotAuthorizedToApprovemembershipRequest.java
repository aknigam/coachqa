package com.coachqa.exception;

import com.coachqa.entity.AppUser;
import com.coachqa.entity.Classroom;

public class NotAuthorizedToApprovemembershipRequest extends BaseAppException {

    private AppUser approver;

    private Classroom classroom;

    public NotAuthorizedToApprovemembershipRequest(AppUser approver, Classroom classroom) {
        super(ApplicationErrorCode.NOT_AUTHORIZEDTO_APPROVE_MEMBERSHIP, String.format("%s is not authorized to approved membership requests for classroom %s", approver.getEmail(), classroom.getClassName()));
    }
}
