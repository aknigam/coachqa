package com.coachqa.exception;

public class InvalidEventForApprovalException extends BaseAppException {
    public InvalidEventForApprovalException(ApplicationErrorCode errorCode, String message) {
        super(errorCode, message);
    }
}
