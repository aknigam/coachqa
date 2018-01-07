package com.coachqa.entity;

import com.coachqa.enums.PostTypeEnum;
import org.joda.time.DateTime;

import java.sql.Timestamp;

/**
 * Created by a.nigam on 23/07/17.
 */
public class PostVote {

    Integer postId; PostTypeEnum postType; Integer postedBy; DateTime postDate; boolean isUpVote;
    public PostVote(Integer postId, PostTypeEnum postType, Integer postedBy, DateTime postDate, boolean isUpVote) {
        this.postId = postId;
        this.postType = postType;
        this.postedBy = postedBy;
        this.postDate = postDate;
        this.isUpVote = isUpVote;
    }

    public Integer getPostId() {
        return postId;
    }

    public void setPostId(Integer postId) {
        this.postId = postId;
    }

    public PostTypeEnum getPostType() {
        return postType;
    }

    public void setPostType(PostTypeEnum postType) {
        this.postType = postType;
    }

    public Integer getPostedBy() {
        return postedBy;
    }

    public void setPostedBy(Integer postedBy) {
        this.postedBy = postedBy;
    }

    public DateTime getPostDate() {
        return postDate;
    }

    public void setPostDate(DateTime postDate) {
        this.postDate = postDate;
    }

    public boolean isUpVote() {
        return isUpVote;
    }

    public void setUpVote(boolean upVote) {
        isUpVote = upVote;
    }
}
