package com.coachqa.service;

import com.coachqa.entity.AppUser;
import com.coachqa.entity.Classroom;
import com.coachqa.ws.model.ClassroomMembershipRequest;

public interface ClassroomService {

	Classroom getClassroom(Integer classroomId);

	void requestClassroomMembership(Integer appUserId, Integer classroomId, String comments);

	Classroom createClassroom(Classroom classroom);

	Classroom getClassroomByName(String classname);

	void leaveClassroom(Integer classroomId, Integer requestedByUserId, Integer memberId, String comments);

	void processJoinRequest(AppUser user, ClassroomMembershipRequest membershipRequests);

	ClassroomMembershipRequest getMemberShipRequests(AppUser user, Integer classroomId);

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
