package com.coachqa.exception;

/**
 * Created by Anand on 10/2/2015.
 */
public class NotAuthorizedtoExistClassroomException extends BaseAppException {

    public NotAuthorizedtoExistClassroomException(){
        super(ApplicationErrorCode.NOT_AUTHORIZEDTOEXIST_CLASSROOM, "Only the owner of the classroom or the member himself can request to leave.");
    }
    public NotAuthorizedtoExistClassroomException(ApplicationErrorCode errorCode, String message) {
        super(errorCode, message);
    }
}
