package com.coachqa.ws.controllor;

import com.coachqa.entity.AppUser;
import com.coachqa.service.ApprovalProcessor;
import com.coachqa.service.UserService;
import com.coachqa.ws.util.WSUtil;
import notification.NotificationService;
import notification.entity.ApplicationEvent;
import notification.entity.EventType;
import notification.impl.NotificationServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@Deprecated
public class ApprovalControllor {


    private static Logger LOGGER = LoggerFactory.getLogger(ApprovalControllor.class);

    @Autowired
    private UserService userService;



    @Autowired
    private NotificationService notificationService;


    @Autowired
    private ApprovalProcessorFactory approvalProcessorFactory;

    /*
                String content = post.getContent();
                EventTypeD eventType = event.getEventType();
                AppUser postedBy = post.getPostedBy();
                String message  = getApprovalMessage(content, eventType, postedBy );
     */
    // TODO: 19/04/17 Incomplete
    @RequestMapping(value="/approve/{eventId}/{isApproved}", method = RequestMethod.POST)
    public void approveRequests(@PathVariable(value = "eventId") int eventId,
                                @PathVariable(value = "isApproved") int isApproved){
        boolean isRequestApproved = isApproved == 0 ? true : false;
        AppUser approver = WSUtil.getUser(userService);



        /*
        Steps:
        A. First we need to ensure that approver is authorised to approve. This means that the approver should be one of the following:
            1. Admin for content approval
            2. Classroom owner for join/leave request
            3. Etc
        B. If the approval action has already happened then what should happen -
            * Unapproved can be approved but not the reverse. So the possible transitions are following:
            * PENDING -> APPROVED
            * UNAPPROVED -> APPROVED
            * There is a use case that even APPROVED can be unapproved if the approval happened by mistake.
        C. Approve/disapprove the request.
        D. Notify the users

         */

        ApplicationEvent event = notificationService.fetchEventDetails(eventId);
        EventType eventType = event.getEventType();
        ApprovalProcessor processor = approvalProcessorFactory.getApprovalProcessor(eventType);



        processor.processApprovalRequest(event,approver, isRequestApproved);

        // possibly event can be deleted here

        notificationService.deleteEvent(eventId);
        return;


    }







}
