package com.coachqa.ws.model;

import com.coachqa.entity.AppUser;
import com.coachqa.entity.Classroom;
import com.coachqa.enums.ClassroomMembershipStatusEnum;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * this class can be used to represent following
 * 1. Membership
 * 2. Membership request (leave join)
 */
public class ClassroomMembership {

    private int membershipId;
    private int classroomId;
    private Classroom classroom;
    private int memberId;
    private AppUser member;
    private ClassroomMembershipStatusEnum membershipStatus;

    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss")
    private Date startDate;

    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss")
    private Date expirationDate;

    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss")
    private Date membershipRequestDate;

    private String requestComments;


    public int getMembershipId() {
        return membershipId;
    }

    public void setMembershipId(int membershipId) {
        this.membershipId = membershipId;
    }

    public int getClassroomId() {
        return classroomId;
    }

    public void setClassroomId(int classroomId) {
        this.classroomId = classroomId;
    }

    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public ClassroomMembershipStatusEnum getMembershipStatus() {
        return membershipStatus;
    }

    public void setMembershipStatus(ClassroomMembershipStatusEnum membershipStatus) {
        this.membershipStatus = membershipStatus;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    public Date getMembershipRequestDate() {
        return membershipRequestDate;
    }

    public void setMembershipRequestDate(Date membershipRequestDate) {
        this.membershipRequestDate = membershipRequestDate;
    }

    public String getRequestComments() {
        return requestComments;
    }

    public void setRequestComments(String requestComments) {
        this.requestComments = requestComments;
    }

    public AppUser getMember() {
        return member;
    }

    public void setMember(AppUser member) {
        this.member = member;
    }

    public Classroom getClassroom() {
        return classroom;
    }

    public void setClassroom(Classroom classroom) {
        this.classroom = classroom;
    }
}
