package com.coachqa.enums;

/**
 * Created by Anand on 9/27/2015.
 */
public enum ClassroomMembershipStatusEnum {
    NOT_MEMBER(0, "Not a member", "Not a member"),
    PENDING_APPROVAL(1, "Pending Approval", "applied for membership"),
    ACTIVE(2, "Active", "Request to join is approved"),
    EXPIRED(3, "Expired", "Request to join is approved"),
    REJECTED(4,"Rejected" , "Rejected" );

    private final String name;

    public Integer getId() {
        return id;
    }

    private final Integer id;
    private final String description;


    ClassroomMembershipStatusEnum(int id, String name, String description) {
        this.name = name;
        this.id = id;
        this.description = description;
    }




}
