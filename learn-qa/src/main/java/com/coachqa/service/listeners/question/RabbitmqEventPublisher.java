package com.coachqa.service.listeners.question;

import com.coachqa.service.listeners.ApplicationEventListener;
import notification.entity.ApplicationEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Queue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class RabbitmqEventPublisher<E> implements EventPublisher<E> {

    private static final Logger LOGGER = LoggerFactory.getLogger(RabbitmqEventPublisher.class);

    @Override
    public void publishEvent(ApplicationEvent<E> event) {

    }
}
