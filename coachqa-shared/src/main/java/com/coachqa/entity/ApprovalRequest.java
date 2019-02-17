package com.coachqa.entity;

import java.util.Date;

/*

Post approval

- Link<Answer> posted by Link<Anand> in the <classroom> waiting for your approval
- New Link<question> posted by Link<Anand> waiting for your approval
- Approval requested by Link<Anand> for membership in Link<classroom name>

 */
public class ApprovalRequest {

    private AppUser requestedBy;

    private Date requestedOn;

    public  ApprovalRequest(){}

    public AppUser getRequestedBy() {
        return requestedBy;
    }

    public void setRequestedBy(AppUser requestedBy) {
        this.requestedBy = requestedBy;
    }

    public Date getRequestedOn() {
        return requestedOn;
    }

    public void setRequestedOn(Date requestedOn) {
        this.requestedOn = requestedOn;
    }
}
