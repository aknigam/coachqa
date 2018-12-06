package com.coachqa.service;

import com.coachqa.entity.AppUser;
import com.coachqa.entity.Classroom;
import com.coachqa.entity.ClassroomSettings;
import com.coachqa.ws.model.ClassroomMembership;

import java.util.List;

public interface ClassroomService {

	Classroom getClassroom(Integer classroomId);

	void requestClassroomMembership(Integer appUserId, Integer classroomId, String comments);

	Classroom createClassroom(Classroom classroom);

	Classroom getClassroomByName(String classname);

	void leaveClassroom(int requestedByUserId, Integer membershipId);

	void processJoinRequest(AppUser approver, List<Integer> membershipRequests, boolean isApprove);

	List<ClassroomMembership> getMemberShipRequests(AppUser user, Integer classroomId);

	boolean isActiveMemberOf(Integer classroomId, int user);

	List<Classroom> getUserMemberships(AppUser user);

//	void approveMembershipRequest(Integer classroomId, Integer userId);

	ClassroomSettings getClassroomSettings(Integer classroomId);

    List<Classroom> searchClassrooms(Integer integer, Integer page, boolean onlyLoginUserClassrooms);

//	void processClassroomJoinRequest(int classroomId, boolean isApproved, int requesterId, Integer approverUserId);

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
