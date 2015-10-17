package com.coachqa.exception;

/**
 * Created by Anand on 10/2/2015.
 */
public class NotAClassroomMemberException extends BaseAppException {
    public NotAClassroomMemberException(ApplicationErrorCode errorCode, String message) {
        super(errorCode, message);
    }
}
