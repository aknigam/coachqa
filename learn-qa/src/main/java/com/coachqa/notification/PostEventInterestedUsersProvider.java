package com.coachqa.notification;

import com.coachqa.entity.AppUser;
import com.coachqa.entity.Classroom;
import com.coachqa.entity.Post;
import com.coachqa.enums.PostTypeEnum;
import com.coachqa.exception.QAEntityNotFoundException;
import com.coachqa.service.ClassroomService;
import com.coachqa.service.PostService;
import com.coachqa.service.UserService;
import com.coachqa.service.impl.ClassroomsServiceImpl;
import notification.EventRegisteredUsersProvider;
import notification.entity.ApplicationEvent;
import notification.entity.EventStage;
import notification.entity.EventType;
import notification.repository.EventRegistrationDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 *
 * This provides the following:
 *
 * - Post owner
 * - If post belongs to a class then the
 *
 * Created by a.nigam on 28/12/16.
 */
public class PostEventInterestedUsersProvider implements EventRegisteredUsersProvider {

    private static Logger LOGGER = LoggerFactory.getLogger(PostEventInterestedUsersProvider.class);

    private PostService postService;

    private ClassroomService classroomService;

    private UserService userService;

    public PostEventInterestedUsersProvider(PostService postService, ClassroomService classroomService, UserService userService){
        this.postService = postService;
        this.classroomService = classroomService;
        this.userService = userService;
        Assert.notNull(postService, "Post service is mandatory for creation of "+ this.getClass().getName());
        Assert.notNull(classroomService, "Classroom service is mandatory for creation of "+ this.getClass().getName());
        Assert.notNull(classroomService, "User service is mandatory for creation of "+ this.getClass().getName());
    }



    @Override
    public List<Integer> getUsersRegisteredByDefault(ApplicationEvent event, EventRegistrationDao eventRegistrationDao) {

        List<Integer> registeredUsers =  new ArrayList<Integer>();
        Integer postId = event.getEventSource();
        try{
            Post post = postService.getPostById(postId);

            if(event.getStage() == EventStage.STAGE_ONE){
                // this is approval flow
                String content = post.getContent();
                EventType eventType = event.getEventType();
                AppUser postedBy = post.getPostedBy();
                registeredUsers.add(postedBy.getAppUserId());
                if(post.getPostTypeEnum() == PostTypeEnum.ANSWER) {
                    // all the people who have either asked the question or answered the question should be alerted
                    registeredUsers.addAll(classroomService.getAllContributors(postId));
                }
                Classroom c = post.getClassroom();
                if(c != null && c.getClassroomId() !=  null) {
                    Classroom clasroom = classroomService.getClassroom(post.getClassroom().getClassroomId());
                    return Arrays.asList(clasroom.getClassOwner().getAppUserId());

                }
                return userService.getPublicPostContentApprovers();
            }

            // if not approved - return the list of approver. we can use stage to identify if it is approved

            Integer classroomId = post.getClassroom().getClassroomId();
            ApplicationEvent classroomEvent = new ApplicationEvent(event.getEventType(), classroomId, event.getEventRaisedByEntityId());
            registeredUsers.addAll(eventRegistrationDao.getRegisteredUsers(classroomEvent));
            Classroom classroom = classroomService.getClassroom(classroomId);
            if(classroom != null)
            {
                registeredUsers.addAll(classroomService.getMembersList(classroomId));
                registeredUsers.add(classroom.getClassOwner().getAppUserId());

            }
            registeredUsers.add(post.getPostedBy().getAppUserId());
            return registeredUsers;
        }
        catch (QAEntityNotFoundException e){
            LOGGER.error("Error ",e);
            return Collections.emptyList();
        }


    }

    @Override
    public List<Integer> getUserSubscribedEntitiesByDefault(int i) {
        return null;
    }

    private String getApprovalMessage(String content, EventType eventType, AppUser postedBy) {
        return String.format("Approve the %s by %d user. \n The conten is as follows:\n %s", eventType.name(), postedBy.getAppUserId(), content);
    }
}
