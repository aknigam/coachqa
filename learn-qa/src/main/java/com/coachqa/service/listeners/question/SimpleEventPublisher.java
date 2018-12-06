package com.coachqa.service.listeners.question;

import com.coachqa.service.listeners.ApplicationEventListener;
import com.coachqa.util.CollectionUtils;
import notification.entity.ApplicationEvent;
import notification.entity.EventType;
import notification.repository.EventDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.BlockingQueue;

public class SimpleEventPublisher implements EventPublisher {

    private boolean persistent = true;
    @Autowired
    private EventDAO eventDAO;

    private static final Logger LOGGER = LoggerFactory.getLogger(SimpleEventPublisher.class);


//    private ApplicationEventListener stageOneListener;
    private Map<EventType, List<ApplicationEventListener>> eventListeners = new HashMap<>();

    private ApplicationEventListener defaultListener;

    private final BlockingQueue questionUpdatesQueue;

    public SimpleEventPublisher(BlockingQueue questionUpdatesQueue){
        this.questionUpdatesQueue =questionUpdatesQueue;

    }


    public void publishEvent(ApplicationEvent event) {
        try {
            Queue<ApplicationEvent> queue = questionUpdatesQueue;
            if (persistent) {
                eventDAO.createEvent(event);
            }
            queue.offer(event);
        }
        catch (Throwable t) {
            LOGGER.error("Error queuing the event "+ event);
        }
    }

}
