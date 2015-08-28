package com.coachqa.entity;

import org.joda.time.DateTime;

import java.sql.Time;

/**
 * Created by anigam on 7/3/15.
 */
public class Vote {

    private int postId;
    private int votedByUserId;
    private DateTime voteDate;
    private boolean upOrDown;

    public Vote(int votedByUserId, boolean upOrDown, DateTime voteDate) {
        this.votedByUserId = votedByUserId ;
        this.upOrDown = upOrDown;
        this.voteDate = voteDate;
    }
}
