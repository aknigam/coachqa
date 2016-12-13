package com.coachqa.exception;

/**
 * Created by Anand on 9/19/2015.
 */
public class BaseAppException  extends  RuntimeException{


    private ApplicationErrorCode errorCode;

    public BaseAppException(ApplicationErrorCode errorCode, String message){
        super(message);
        this.errorCode = errorCode;
    }

    public BaseAppException(ApplicationErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }
}
