package com.coachqa.exception;

/**
 * Created by a.nigam on 10/04/17.
 */
public class QAEntityNotFoundException extends  BaseAppException  {

    public QAEntityNotFoundException(ApplicationErrorCode errorCode, String message) {
        super(errorCode, message);
    }
}
