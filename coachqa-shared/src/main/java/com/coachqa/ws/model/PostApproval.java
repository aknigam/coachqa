package com.coachqa.ws.model;

import com.coachqa.entity.AppUser;
import com.coachqa.enums.PostTypeEnum;

import javax.validation.constraints.NotNull;

/**
 * Created by a.nigam on 28/12/16.
 */
public class PostApproval {

    private AppUser approvedBy;
    @NotNull
    private Integer postId;

    private boolean isApproved;
    @NotNull
    private String comments;

    @NotNull
    private PostTypeEnum postType;

    public PostApproval(){}

    public AppUser getApprovedBy() {
        return approvedBy;
    }

    public void setApprovedBy(AppUser approvedBy) {
        this.approvedBy = approvedBy;
    }

    public Integer getPostId() {
        return postId;
    }

    public void setPostId(Integer postId) {
        this.postId = postId;
    }

    public boolean isApproved() {
        return isApproved;
    }

    public void setApproved(boolean approved) {
        isApproved = approved;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public PostTypeEnum getPostType() {
        return postType;
    }

    public void setPostType(PostTypeEnum postType) {
        this.postType = postType;
    }
}
