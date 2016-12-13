package com.coachqa.exception;

/**
 * Created by a.nigam on 13/12/16.
 */
public class AnswerPostException extends BaseAppException {
    public AnswerPostException(ApplicationErrorCode errorCode) {
        super(errorCode);
    }
}
