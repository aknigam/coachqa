package com.coachqa.service.listeners;

import notification.entity.ApplicationEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Retries 5 times and then exists
 */
public class SimpleRetryingEventListener implements  ApplicationEventListener {

    private static Logger LOGGER = LoggerFactory.getLogger(SimpleRetryingEventListener.class);

    private static final int MAX_RETY_COUNT = 5;

    private  ApplicationEventListener listener;

    public SimpleRetryingEventListener(ApplicationEventListener listener) {
        this.listener = listener;
    }

    @Override
    public void onEvent(ApplicationEvent event) {
        boolean success= false;
        for (int i = 0; i < MAX_RETY_COUNT; i++) {
            try{
                listener.onEvent(event);
                success = true;
            }
            catch(Exception e){
                // retry logic should come here
                LOGGER.debug(String.format("Exception occurred during event processing. Retrying %d times", i), e);
            }
            if(success)
                break;
        }
        if(!success)
        {
            LOGGER.error("Event "+event+", could not be processed after " + MAX_RETY_COUNT + " number of retrials");
        }



    }
}
