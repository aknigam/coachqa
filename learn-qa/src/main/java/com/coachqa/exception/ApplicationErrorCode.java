package com.coachqa.exception;

/**
 * Created by Anand on 9/19/2015.
 */
public enum ApplicationErrorCode {

    // resource not founnd

    TAG_NOT_FOUND(1001, "Tag not found"),
    TAG_ALREADY_EXISTS(1002,"Tag already exists" ),

    TAGS_REQUIRED_FOR_QUESTION(1003, "Question cannot be added without tags."),

    USER_ALREADY_EXISTS(2001, "User already exists"),

    CLASSROOM_NOT_FOUND(3001, "Classroon not exists" ),
    NOT_AUTHORIZEDTOEXIST_CLASSROOM(3002, "Only the owner of the classroom or the member himself can request to leave"),
    INVALID_MEMBERSHIP(3003,"Member not a member" ),
    NOT_AUTHORIZEDTO_APPROVE_MEMBERSHIP(3004, "Not authorized to approve membership request"),
    NOT_AUTHORIZEDTO_VIEW_MEMBERSHIP_REQUESTS(3005),
    CLASSROOM_ALREADY_EXISTS(3006 ),

    QUESTION_POST_PRIVATE(4000, "Private question can only be posted to a valid classroom"),
    QUESTION_POST_CLASSROOM(4001, "You need to be a member of the classroom for posting the question"),

    ANSWER_PRIVATE_QUESTION(5001,"Cannot answer a private question" );


    private final int errorCode;
    private final String message;

    ApplicationErrorCode(int errorCode, String message) {
        this.errorCode = errorCode;
        this.message = message;
    }

    ApplicationErrorCode(int errorCode) {
        this.errorCode = errorCode;
        this.message= "";
    }

    public int getErrorCode() {
        return errorCode;
    }

    public String getMessage() {
        return message;
    }


}
