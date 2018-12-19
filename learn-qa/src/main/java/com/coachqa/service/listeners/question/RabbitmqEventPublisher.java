package com.coachqa.service.listeners.question;

import notification.entity.ApplicationEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RabbitmqEventPublisher implements EventPublisher {

    private static final Logger LOGGER = LoggerFactory.getLogger(RabbitmqEventPublisher.class);

    @Override
    public void publishEvent(ApplicationEvent event) {

    }

}
