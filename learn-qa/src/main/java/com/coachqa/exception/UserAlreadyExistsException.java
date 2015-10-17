package com.coachqa.exception;

/**
 * Created by Anand on 9/27/2015.
 */
public class UserAlreadyExistsException extends BaseAppException {

    public UserAlreadyExistsException(ApplicationErrorCode errorCode, String message) {
        super(errorCode, message);
    }
}
