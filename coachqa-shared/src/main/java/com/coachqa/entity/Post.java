package com.coachqa.entity;

import com.coachqa.enums.PostTypeEnum;


import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * Created by Anand on 8/25/2015.
 */
public class Post {

    
    private Integer postId;

    private PostTypeEnum postTypeEnum;
    
    private Integer noOfViews;
    
    private AppUser postedBy;
    
    private Date postDate;
    
    private Integer votes;
    
    private String content;

    // value 1 means pending approval, 0 means approved
    private boolean approvalStatus;
    
    private String approvalComment;

    private Integer classroomId;


    public Post(){

    }

    public Integer getPostId() {
        return postId;
    }

    public void setPostId(Integer postId) {
        this.postId = postId;
    }

    public Integer getNoOfViews() {
        return noOfViews;
    }

    public void setNoOfViews(Integer noOfViews) {
        this.noOfViews = noOfViews;
    }

    public AppUser getPostedBy() {
        return postedBy;
    }

    public void setPostedBy(AppUser postedBy) {
        this.postedBy = postedBy;
    }

    public Date getPostDate() {
        return postDate;
    }

    public void setPostDate(Date postDate) {
        this.postDate = postDate;
    }

    public Integer getVotes() {
        return votes;
    }

    public void setVotes(Integer votes) {
        this.votes = votes;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
    
    public boolean getApprovalStatus() {
        return approvalStatus;
    }

    public void setApprovalStatus(boolean approvalStatus) {
        this.approvalStatus = approvalStatus;
    }

    public String getApprovalComment() {
        return approvalComment;
    }

    public void setApprovalComment(String approvalComment) {
        this.approvalComment = approvalComment;
    }

    public PostTypeEnum getPostTypeEnum() {
        return postTypeEnum;
    }

    public void setPostTypeEnum(PostTypeEnum postTypeEnum) {
        this.postTypeEnum = postTypeEnum;
    }


    public Integer getClassroomId() {
        return classroomId;
    }

    public void setClassroomId(Integer classroomId) {
        this.classroomId = classroomId;
    }
}
