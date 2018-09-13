package com.coachqa.notification;

import com.coachqa.entity.AppUser;
import com.coachqa.entity.Classroom;
import com.coachqa.entity.Post;
import com.coachqa.exception.QAEntityNotFoundException;
import com.coachqa.service.ClassroomService;
import com.coachqa.service.PostService;
import com.coachqa.service.UserService;
import notification.EventRegisteredUsersProvider;
import notification.entity.ApplicationEvent;
import notification.entity.EventStage;
import notification.entity.EventType;
import notification.repository.EventRegistrationDao;
import org.springframework.util.Assert;

import java.util.ArrayList;
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
    public List<Integer> getUsersRegisteredByDefault(ApplicationEvent<Integer> event, EventRegistrationDao eventRegistrationDao) {

        List<Integer> registeredUsers =  new ArrayList<>();
        Integer postId = event.getEventSource();
        try{
            Post post = postService.getPostById(postId);
            EventType type = event.getEventType();

            if(event.getStage() == EventStage.STAGE_ONE){
                // this is approval flow
                String content = post.getContent();
                EventType eventType = event.getEventType();
                AppUser postedBy = post.getPostedBy();

                return userService.getPostContentApprovers();
            }

            // if not approved - return the list of approver. we can use stage to identify if it is approved

            Integer classroomId = post.getClassroomId();
            Classroom classroom = classroomService.getClassroom(classroomId);
            if(classroom != null)
            {
                ApplicationEvent<Integer> classroomEvent = new ApplicationEvent<Integer>(event.getEventType(), classroomId, event.getEventRaisedByEntityId());
                registeredUsers.addAll(eventRegistrationDao.getRegisteredUsers(classroomEvent));
                registeredUsers.add(classroom.getClassOwner().getAppUserId());

            }

            return registeredUsers;
        }
        catch (QAEntityNotFoundException e){
            return Collections.emptyList();
        }


    }

    private String getApprovalMessage(String content, EventType eventType, AppUser postedBy) {
        return String.format("Approve the %s by %d user. \n The conten is as follows:\n %s", eventType.name(), postedBy.getAppUserId(), content);
    }
}
