package com.coachqa.entity;

import org.joda.time.DateTime;

import java.sql.Time;

/**
 * Created by anigam on 7/3/15.
 */
public class QuestionVote extends Vote {

    private int questionId;

    public QuestionVote(int votedByUserId, boolean upOrDown, DateTime voteDate, int questionId) {
        super(votedByUserId, upOrDown, voteDate);
        this.questionId = questionId;
    }
}