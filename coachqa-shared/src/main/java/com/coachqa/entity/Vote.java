package com.coachqa.entity;

import org.joda.time.DateTime;

/**
 * Created by anigam on 7/3/15.
 */
public class Vote {

    private int postId;
    private int votedByUserId;
    private DateTime voteDate;
    private boolean upOrDown;

    public Vote(){}

    public Vote(int votedByUserId, boolean upOrDown, DateTime voteDate) {
        this.votedByUserId = votedByUserId ;
        this.upOrDown = upOrDown;
        this.voteDate = voteDate;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public int getVotedByUserId() {
        return votedByUserId;
    }

    public void setVotedByUserId(int votedByUserId) {
        this.votedByUserId = votedByUserId;
    }

    public DateTime getVoteDate() {
        return voteDate;
    }

    public void setVoteDate(DateTime voteDate) {
        this.voteDate = voteDate;
    }

    public boolean isUpOrDown() {
        return upOrDown;
    }

    public void setUpOrDown(boolean upOrDown) {
        this.upOrDown = upOrDown;
    }
}
