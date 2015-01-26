package com.coachqa.service.impl;

import org.springframework.stereotype.Component;

import com.coachqa.entity.Classroom;
import com.coachqa.repository.dao.ClassroomDAO;
import com.coachqa.service.ClassroomService;

@Component
public class ClassroomsServiceImpl implements ClassroomService{

	private ClassroomDAO classroomDAO;
	@Override
	public Classroom getClassroom(Integer classroomId) {
		return classroomDAO.getClassroomByIdentifier(classroomId);
	}
	@Override
	public void joinClassroom(Integer appUserId, Integer classroomId) {
		classroomDAO.joinClassroom(appUserId, classroomId);
	}


}
