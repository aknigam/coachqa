package com.coachqa.repository.dao;

import com.coachqa.entity.AppUser;
import com.coachqa.entity.Classroom;
import com.coachqa.enums.ClassroomMembershipStatusEnum;
import com.coachqa.ws.model.ClassroomMembershipRequest;

import java.util.List;

public interface ClassroomDAO {

	void joinClassroom(Integer appUserId, Integer classroomId, ClassroomMembershipStatusEnum pendingApproval, String comments);

	Classroom getClassroomByIdentifier(Integer classroomId);


	Classroom createClassroom(Classroom classroom);

	void endMembership(Integer classroomId, Integer memberId, String comments);

	void findRequestAndApprove(boolean approve, Integer classroomId, Integer userId, String comments);

	ClassroomMembershipRequest getMembershipRequests(Integer classroomId);

	List<Classroom> getUserMemberships(AppUser user);
}
