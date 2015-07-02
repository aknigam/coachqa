package com.coachqa.service.listeners.question;

import com.coachqa.entity.Question;
import com.coachqa.service.QuestionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class ImageToTextQuestionConverterQuestionListener extends QuestionPostListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(ImageToTextQuestionConverterQuestionListener.class);

    @Autowired
    private QuestionService questionService;

    @Override
    public void questionPosted(Integer questionId) {
        LOGGER.info("Question posted: "+ questionId);
        sendNotificationIfQuestionHasImage(questionId);
    }

    @Override
    public void questionUpdated(Integer questionId) {
        LOGGER.info("Question updated: "+ questionId);
        sendNotificationIfQuestionHasImage(questionId);
    }

    private void sendNotificationIfQuestionHasImage(Integer questionId) {
        Question question = questionService.getQuestionById(questionId);
        if(question.hasImage()){
            // send notification to convert image to text.
        }
    }

    @Override
    public void questionAnswered(Integer questionId) {

    }
}
