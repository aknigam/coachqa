package com.coachqa.exception;

/**
 * Created by a.nigam on 21/04/17.
 */
public class NotAuthorisedToApproveException extends BaseAppException{
    public NotAuthorisedToApproveException(ApplicationErrorCode errorCode, String message) {
        super(errorCode, message);
    }
}
