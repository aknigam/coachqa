package com.coachqa.exception;

/**
 * Created by Anand on 9/19/2015.
 */
public enum ApplicationErrorCode {

    // resource not founnd

    TAG_NOT_FOUND(1001, "Tag not found"),
    TAG_ALREADY_EXISTS(1002,"Tag already exists" );






    private final int errorCode;
    private final String message;

    ApplicationErrorCode(int errorCode, String message) {
        this.errorCode = errorCode;
        this.message = message;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public String getMessage() {
        return message;
    }
}
