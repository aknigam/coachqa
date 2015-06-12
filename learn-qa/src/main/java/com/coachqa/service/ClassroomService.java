package com.coachqa.service;

import com.coachqa.entity.Classroom;

public interface ClassroomService {

	Classroom getClassroom(Integer classroomId);

	void joinClassroom(Integer appUserId, Integer classroomId);

	static class ClassroomJoinRequest
	{
		private Integer appUserId;
		private Integer classroomId;
		
		public ClassroomJoinRequest(Integer classroomId, Integer appUserId ) {
			this.appUserId = appUserId;
			this.classroomId = classroomId;
		}

		public Integer getAppUserId() {
			return appUserId;
		}

		public Integer getClassroomId() {
			return classroomId;
		}
	}


	
}
