package com.coachqa.service;

import com.coachqa.entity.AppUser;
import com.coachqa.entity.Classroom;
import com.coachqa.ws.model.ClassroomMembershipRequest;

import java.util.List;

public interface ClassroomService {

	Classroom getClassroom(Integer classroomId);

	void requestClassroomMembership(Integer appUserId, Integer classroomId, String comments);

	Classroom createClassroom(Classroom classroom);

	Classroom getClassroomByName(String classname);

	void leaveClassroom(Integer classroomId, Integer requestedByUserId, Integer memberId, String comments);

	void processJoinRequest(AppUser user, ClassroomMembershipRequest membershipRequests);

	ClassroomMembershipRequest getMemberShipRequests(AppUser user, Integer classroomId);

	boolean isMemberOf(Integer classroomId, int user);

	List<Classroom> getUserMemberships(AppUser user);

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
