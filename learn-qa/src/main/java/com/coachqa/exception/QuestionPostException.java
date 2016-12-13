package com.coachqa.exception;

/**
 * Created by a.nigam on 13/12/16.
 */
public class QuestionPostException extends BaseAppException {
    public QuestionPostException(ApplicationErrorCode errorCode, String message) {
        super(errorCode, message);
    }

    public QuestionPostException(ApplicationErrorCode errorCode) {
        super(errorCode);
    }
}
