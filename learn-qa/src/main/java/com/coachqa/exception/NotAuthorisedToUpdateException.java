package com.coachqa.exception;

/**
 * Created by a.nigam on 21/04/17.
 */
public class NotAuthorisedToUpdateException extends BaseAppException{
    public NotAuthorisedToUpdateException(ApplicationErrorCode errorCode, String message) {
        super(errorCode, message);
    }
}
