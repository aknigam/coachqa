package com.coachqa.service.listeners.question;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by anigam on 6/17/15.
 */
public class IndexQuestionListener extends QuestionPostListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(IndexQuestionListener.class);

    @Override
    public void questionPosted(Integer questionId) {
        LOGGER.info("Question posted: "+ questionId);
    }

    @Override
    public void questionUpdated(Integer questionId) {
        LOGGER.info("Question updated: "+ questionId);
    }

    @Override
    public void questionAnswered(Integer questionId) {

    }
}
