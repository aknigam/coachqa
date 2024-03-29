package com.coachqa.entity;

import com.coachqa.enums.PostTypeEnum;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * Created by Anand on 8/25/2015.
 */
public class Post extends AccountEntity {

    private Integer postId;

    private PostTypeEnum postTypeEnum;
    
    private Integer noOfViews= 0;
    
    private AppUser postedBy;

    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss")
    private Date postDate;
    
    private Integer votes;
    
    private String content;

    // value 1 means true (approved) , 0 (false) means pending approval
    private boolean approvalStatus;
    
    private String approvalComment;

    private Classroom classroom;

    private boolean myPost = false;
    private boolean loggedInUserCanApprove = false;

    public boolean isMyPost() {
        return myPost;
    }

    public void setMyPost(boolean myPost) {
        this.myPost = myPost;
    }

    public Post(){

    }

    public Classroom getClassroom() {
        return classroom;
    }

    public void setClassroom(Classroom classroom) {
        this.classroom = classroom;
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
        if(noOfViews != null){
            this.noOfViews = noOfViews;
        }

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

    public void loggedInUserCanApprove(boolean canApprove) {
        loggedInUserCanApprove = canApprove;
    }

    public boolean isLoggedInUserCanApprove() {
        return loggedInUserCanApprove;
    }
}
