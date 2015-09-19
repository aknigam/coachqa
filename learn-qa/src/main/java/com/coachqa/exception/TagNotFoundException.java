package com.coachqa.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by Anand on 9/19/2015.
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class TagNotFoundException extends BaseAppException {

    public TagNotFoundException(ApplicationErrorCode errorCode, String tagName) {
        super(errorCode, "Tag with name ["+ tagName+"] not found.");
    }
}
