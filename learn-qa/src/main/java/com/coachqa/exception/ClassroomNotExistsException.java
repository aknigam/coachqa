package com.coachqa.exception;

/**
 * Created by Anand on 10/2/2015.
 */
public class ClassroomNotExistsException extends BaseAppException {

    public ClassroomNotExistsException(ApplicationErrorCode errorCode, String message) {
        super(errorCode, message);
    }
}
