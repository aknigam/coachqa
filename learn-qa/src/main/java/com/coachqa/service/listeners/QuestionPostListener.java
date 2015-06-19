package com.coachqa.service.listeners;


/**
 * Following tye of listeners can come into action when a question is added.
 */
public interface QuestionPostListener {

    public void questionPosted(Integer questionId);

    public void questionUpdated(Integer questionId);

    public void questionAnswered(int questionId);

}
