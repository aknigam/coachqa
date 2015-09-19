package com.coachqa.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by Anand on 9/19/2015.
 */
@ResponseStatus(HttpStatus.ALREADY_REPORTED)
public class TagAlreadyExistsException extends BaseAppException {

    public TagAlreadyExistsException(ApplicationErrorCode errorCode, String tagName) {
        super(errorCode, "Tag ["+ tagName+"] already exists.");
    }
}
