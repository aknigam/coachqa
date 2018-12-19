package com.coachqa.service.listeners.question;

import notification.entity.ApplicationEvent;


/**
 * Event publisher needs to invoke certain listeners/consumers in specific order. To achive this we can associate
 * a STAGE with the event object. Each consumer can update its status in the message metadata.
 * Need to implement chain of responsibility design pattern in which each element in the chain can work a-synchronously.
 *
 *      ConsumersSetA[Stage-1]---->ConsumersSetB[Stage-1]---->ConsumersSetC[Stage-1]---->ConsumersSetD[Stage-1]
 *
 * Consumers of the intermediate stages should republish the message after processing so that other consumers in the
 * chain can process them. This is tricky as it can lead to duplicate publication of messages.
 *
 * To keep things simple we will have only two stages
 * STAGE-1 content approval
 * STAGE-2 all the other consumers
 *
 *
 */
public interface EventPublisher {

    void publishEvent(ApplicationEvent event);


}
