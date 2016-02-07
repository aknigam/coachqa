package com.coachqa.ws.model;

import java.util.List;

public class ClassroomMembershipRequest {

    private int classroomId;

    private Boolean approve;

    private List<MembershipRequest> requests;

    private String comments;

    public ClassroomMembershipRequest(){}

    public ClassroomMembershipRequest(Integer classroomId) {
        this.classroomId = classroomId;
    }

    public int getClassroomId() {
        return classroomId;
    }

    public void setClassroomId(int classroomId) {
        this.classroomId = classroomId;
    }

    public boolean isApprove() {
        return approve;
    }

    public void setApprove(boolean approve) {
        this.approve = approve;
    }


    public List<MembershipRequest> getRequests() {
        return requests;
    }

    public void setRequests(List<MembershipRequest> requests) {
        this.requests = requests;
    }

    public Boolean getApprove() {
        return approve;
    }

    public void setApprove(Boolean approve) {
        this.approve = approve;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }
}
