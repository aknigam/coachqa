package com.coachqa.exception;

import com.coachqa.entity.AppUser;
import com.coachqa.entity.Classroom;

/**
 * Created by Anand on 10/2/2015.
 */
public class NotAuthorisedToViewMembershipRequestsException extends BaseAppException {
    public NotAuthorisedToViewMembershipRequestsException(AppUser user, Classroom classroom) {
        super(ApplicationErrorCode.NOT_AUTHORIZEDTO_VIEW_MEMBERSHIP_REQUESTS, String.format("%s is not authorised to view membership requests for classroom %s", user.getEmail(), classroom.getClassName()));
    }
}
