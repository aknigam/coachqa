package com.coachqa.service.listeners.question;

import com.coachqa.entity.Question;
import com.coachqa.service.QuestionService;
import com.coachqa.service.listeners.ApplicationEventListener;
import notification.entity.ApplicationEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class PostThumbnailCreaterListener implements ApplicationEventListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(PostThumbnailCreaterListener.class);

    @Override
    public void onEvent(ApplicationEvent event) {

    }
}
