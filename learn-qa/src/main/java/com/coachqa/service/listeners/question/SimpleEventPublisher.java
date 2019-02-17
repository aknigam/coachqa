package com.coachqa.service.listeners.question;

import com.coachqa.service.listeners.ApplicationEventListener;
import notification.entity.ApplicationEvent;
import notification.entity.EventType;
import notification.repository.EventDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.BlockingQueue;

public class SimpleEventPublisher implements EventPublisher {

    // TODO: 09/02/19 why is this needed
    private boolean persistent = false;
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
            // sleeping to give main thread the chance to continue processing.
            // TODO: 09/02/19
            sleep(10l);
        }
        catch (Throwable t) {
            LOGGER.error("Error queuing the event "+ event);
        }
    }

    private void sleep(long duration) {
        try {
            Thread.sleep(duration);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
