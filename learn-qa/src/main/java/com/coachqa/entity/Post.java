package com.coachqa.entity;

import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * Created by Anand on 8/25/2015.
 */
public class Post {


    public Integer getPostId() {
        return postId;
    }

    public void setPostId(Integer postId) {
        this.postId = postId;
    }

    public Integer postId;
    private AppUser postedBy;

    private String content;
    private byte[] image;


    private Date postDate;
    private Date lastActiveDate;
    private Integer votes;

    public Post() {
    }

    public Post(int refSubjectId, int questionLevelId, AppUser postedBy,
                    int refQuestionStatusId, String title, String content,
                    Date postDate, Date lastActiveDate, boolean isPublic) {
        this.postedBy = postedBy;
        this.content = content;
        this.postDate = postDate;
        this.lastActiveDate = lastActiveDate;
       }

    public Post(int refSubjectId, int questionLevelId, AppUser postedBy,
                    int refQuestionStatusId, String title, String content,
                    Integer noOfViews, Date postDate, Date lastActiveDate,
                    Integer votes, boolean isPublic) {
        this.postedBy = postedBy;
        this.content = content;
        this.postDate = postDate;
        this.lastActiveDate = lastActiveDate;
        this.votes = votes;

    }


    public AppUser getPostedBy() {
        return this.postedBy;
    }

    public void setPostedBy(AppUser postedBy) {
        this.postedBy = postedBy;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getPostDate() {
        return this.postDate;
    }

    public void setPostDate(Date postDate) {
        this.postDate = postDate;
    }

    public Date getLastActiveDate() {
        return this.lastActiveDate;
    }

    public void setLastActiveDate(Date lastActiveDate) {
        this.lastActiveDate = lastActiveDate;
    }

    public Integer getVotes() {
        return this.votes;
    }

    public void setVotes(Integer votes) {
        this.votes = votes;
    }

    public boolean hasImage() {
        return false;
    }

}
