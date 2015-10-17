package com.coachqa.exception;

/**
 * Created by Anand on 10/2/2015.
 */
public class ClassroomAlreadyExistsException extends BaseAppException {
    public ClassroomAlreadyExistsException(String className) {
        super(ApplicationErrorCode.CLASSROOM_ALREADY_EXISTS, String.format("%s classroom already exists", className));
    }
}
