package com.coachqa.ws.model;

import com.coachqa.entity.AppUser;
import org.joda.time.DateTime;

public class MembershipRequest {
    private AppUser user;
    private DateTime requestDate;
    private String comments;

    public MembershipRequest(AppUser user, DateTime requestDate){
        this.user = user;
        this.requestDate = requestDate;
    }
    public MembershipRequest(AppUser user, DateTime requestDate, String comments){
        this(user, requestDate);
        this.comments = comments;
    }

    public AppUser getUser() {
        return user;
    }

    public void setUser(AppUser user) {
        this.user = user;
    }

    public DateTime getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(DateTime requestDate) {
        this.requestDate = requestDate;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }
}
