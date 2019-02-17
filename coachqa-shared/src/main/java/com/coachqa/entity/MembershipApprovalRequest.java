package com.coachqa.entity;

import java.util.Date;

public class MembershipApprovalRequest extends ApprovalRequest {

    private Classroom classroom;

    public MembershipApprovalRequest(){}

    public Classroom getClassroom() {
        return classroom;
    }

    public void setClassroom(Classroom classroom) {
        this.classroom = classroom;
    }
}
