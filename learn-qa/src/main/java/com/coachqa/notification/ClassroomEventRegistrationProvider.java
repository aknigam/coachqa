package com.coachqa.notification;

import com.coachqa.entity.Classroom;
import com.coachqa.repository.dao.ClassroomDAO;
import com.coachqa.service.ClassroomService;
import notification.DefaultEventConsumersProvider;
import notification.entity.ApplicationEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import java.util.Collection;
import java.util.Collections;

public class ClassroomEventRegistrationProvider implements DefaultEventConsumersProvider {

    private final ClassroomService classroomService;

    public ClassroomEventRegistrationProvider(ClassroomService classroomService) {
        this.classroomService = classroomService;
        Assert.notNull(this.classroomService, "Classroom service must not be null");
    }

    @Override
    public Collection<? extends Integer> getUsersRegisteredByDefault(ApplicationEvent<Integer> event) {
        int classroomId = event.getEventSource();
        Classroom classroom = classroomService.getClassroom(classroomId);
        return Collections.singleton(classroom.getClassOwner().getAppUserId());
    }
}
