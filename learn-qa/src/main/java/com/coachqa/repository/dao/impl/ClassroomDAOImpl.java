package com.coachqa.repository.dao.impl;

import org.springframework.beans.factory.InitializingBean;

import com.coachqa.entity.Classroom;
import com.coachqa.repository.dao.ClassroomDAO;
import com.coachqa.repository.dao.sp.ClassroomGetByIdSproc;
import com.coachqa.repository.dao.sp.JoinClassroomSproc;

public class ClassroomDAOImpl extends BaseDao implements InitializingBean, ClassroomDAO {

	private ClassroomGetByIdSproc classroomGetByIdSproc;
	
	
	private JoinClassroomSproc joinClassroomSproc;
	
	
	@Override
	public void afterPropertiesSet() throws Exception {
		classroomGetByIdSproc = new ClassroomGetByIdSproc(getDataSource());
	}

	@Override
	public void joinClassroom(Integer appUserId, Integer classroomId) {
		joinClassroomSproc.joinClassroom(appUserId, classroomId);
	}

	@Override
	public Classroom getClassroomByIdentifier(Integer classroomId) {
		return classroomGetByIdSproc.getClassroomByIdentifier(classroomId);
	}

}
