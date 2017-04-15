package com.coachqa.ws.controllor;

import com.coachqa.entity.AppUser;
import com.coachqa.enums.PostTypeEnum;
import com.coachqa.enums.QuestionRatingEnum;
import com.coachqa.service.PostService;
import com.coachqa.service.UserService;
import com.coachqa.service.listeners.question.EventPublisher;
import com.coachqa.ws.model.PostApproval;
import com.coachqa.ws.util.WSUtil;
import notification.entity.ApplicationEvent;
import notification.entity.EventStage;
import notification.entity.EventType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Both question and answer can be treated as posts. Some common operations like voting, rating etc can be done in a common manner.
 */
@RestController
@RequestMapping("/api/approval")
public class ApprovalControllor {



    @Autowired
    private UserService userService;

    @Autowired
    private PostService postService;

    @Autowired
    @Lazy
    private EventPublisher eventPublisher;

    /*
                String content = post.getContent();
                EventTypeD eventType = event.getEventType();
                AppUser postedBy = post.getPostedBy();
                String message  = getApprovalMessage(content, eventType, postedBy );
     */

    @RequestMapping(value="/approve/{eventtype}/{eventsourceId}/{eventId}/{postedby}", method = RequestMethod.POST)
    public void approveRequests(@PathVariable(value = "eventtype") EventType eventType,
                                @PathVariable(value = "eventsourceId") Integer eventSourceId,
                                @PathVariable(value = "eventId") Integer eventId,
                                @PathVariable(value = "postedby") Integer postedbyUserId){
        AppUser user = WSUtil.getUser(userService);

        if(eventType == EventType.QUESTION_ANSWERED || eventType == EventType.ANSWER_POSTED
                || eventType == EventType.QUESTION_POSTED || eventType == EventType.QUESTION_UPDATED){
            // find the post and set it to approved
        }

        ApplicationEvent applicationEvent = new ApplicationEvent(eventType, eventSourceId, EventStage.STAGE_TWO );
        raiseApplicationEvent(applicationEvent);



    }

    private void raiseApplicationEvent(ApplicationEvent applicationEvent) {
        eventPublisher.publishEvent(applicationEvent);
    }


}
