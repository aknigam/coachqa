package com.coachqa.notification;

import com.coachqa.entity.Classroom;
import com.coachqa.repository.dao.ClassroomDAO;
import notification.DefaultRegistrationProvider;
import notification.entity.ApplicationEvent;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;
import java.util.Collections;

public class ClassroomEventRegistrationProvider implements DefaultRegistrationProvider {

    @Autowired
    private ClassroomDAO classroomDAO;

    @Override
    public Collection<? extends Integer> getUsersRegisteredByDefault(ApplicationEvent<Integer> event) {
        int classroomId = event.getEventSource();
        Classroom classroom = classroomDAO.getClassroomByIdentifier(classroomId);
        return Collections.singleton(classroom.getClassOwner().getAppUserId());
    }
}
