package com.coachqa.exception;

/**
 * Created by Anand on 9/19/2015.
 */
public class TagsRequiredForQuestionException extends BaseAppException {
    public TagsRequiredForQuestionException(ApplicationErrorCode errorCode) {
        super(errorCode, errorCode.getMessage());
    }
}
