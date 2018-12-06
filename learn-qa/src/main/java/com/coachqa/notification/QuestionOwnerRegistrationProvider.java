package com.coachqa.notification;

import com.coachqa.entity.Question;
import com.coachqa.service.QuestionService;
import notification.EventRegisteredUsersProvider;
import notification.entity.ApplicationEvent;
import notification.repository.EventRegistrationDao;
import org.springframework.util.Assert;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by a.nigam on 28/12/16.
 */
public class QuestionOwnerRegistrationProvider implements EventRegisteredUsersProvider {

    private QuestionService questionService;

    public QuestionOwnerRegistrationProvider(QuestionService questionService){
        this.questionService = questionService;
        Assert.notNull(this.questionService, "Question service must not be null");
    }



    @Override
    public List<Integer> getUsersRegisteredByDefault(ApplicationEvent event, EventRegistrationDao eventRegistrationDao) {
        Integer questionId = event.getEventSource();
        Question question = questionService.getQuestionById(questionId);
        if(question != null){
            return Arrays.asList(question.getPostedBy().getAppUserId());
        }
        return Collections.emptyList();
    }

    @Override
    public List<Integer> getUserSubscribedEntitiesByDefault(int i) {
        return null;
    }
}
