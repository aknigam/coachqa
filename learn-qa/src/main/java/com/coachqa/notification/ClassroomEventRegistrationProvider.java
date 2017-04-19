package com.coachqa.notification;

import com.coachqa.entity.Classroom;
import com.coachqa.service.ClassroomService;
import notification.EventRegisteredUsersProvider;
import notification.entity.ApplicationEvent;
import notification.repository.EventRegistrationDao;
import org.springframework.util.Assert;

import java.util.Collections;
import java.util.List;

public class ClassroomEventRegistrationProvider implements EventRegisteredUsersProvider {

    private final ClassroomService classroomService;

    public ClassroomEventRegistrationProvider(ClassroomService classroomService) {
        this.classroomService = classroomService;
        Assert.notNull(this.classroomService, "Classroom service must not be null");
    }

    @Override
    public List<Integer> getUsersRegisteredByDefault(ApplicationEvent<Integer> event, EventRegistrationDao eventRegistrationDao) {
        int classroomId = event.getEventSource();
        Classroom classroom = classroomService.getClassroom(classroomId);
        return Collections.singletonList(classroom.getClassOwner().getAppUserId());
    }


}
