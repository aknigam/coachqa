package com.coachqa.service.listeners.question;

import com.coachqa.service.listeners.ApplicationEventListener;
import notification.entity.ApplicationEvent;
import notification.entity.EventType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RabbitmqEventPublisher<E> implements EventPublisher<E> {

    private static final Logger LOGGER = LoggerFactory.getLogger(RabbitmqEventPublisher.class);

    @Override
    public void publishEvent(ApplicationEvent<E> event) {

    }

    @Override
    public void attachListener(EventType eventType, ApplicationEventListener listener) {

    }
}
