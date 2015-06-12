package com.coachqa.repository.dao;

import com.coachqa.entity.Classroom;

public interface ClassroomDAO {

	void joinClassroom(Integer appUserId, Integer classroomId);

	Classroom getClassroomByIdentifier(Integer classroomId);


}
