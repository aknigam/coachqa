package com.coachqa.service.listeners.question;

import com.coachqa.entity.Question;
import com.coachqa.service.QuestionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class PostThumbnailCreaterListener extends QuestionPostListener{

    private static final Logger LOGGER = LoggerFactory.getLogger(PostThumbnailCreaterListener.class);

    @Autowired
    private QuestionService questionService;

    @Override
    public void questionPosted(Integer questionId) {
        LOGGER.info("Question posted: "+ questionId);
        extractImageAndCreateThumbnail(questionId);
    }

    @Override
    public void questionUpdated(Integer questionId) {
        LOGGER.info("Question updated: "+ questionId);
        extractImageAndCreateThumbnail(questionId);
    }

    private void extractImageAndCreateThumbnail(Integer questionId) {
        Question question = questionService.getQuestionById(questionId);

    }

    @Override
    public void questionAnswered(Integer questionId) {
        extractImageAndCreateThumbnail(questionId);
    }
}
