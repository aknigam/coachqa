package com.coachqa.repository.dao;

import com.coachqa.entity.AppUser;
import com.coachqa.entity.Classroom;
import com.coachqa.enums.ClassroomMembershipStatusEnum;
import com.coachqa.ws.model.ClassroomMembership;

import java.util.List;

public interface ClassroomDAO {

	void joinClassroom(Integer appUserId, Integer classroomId, ClassroomMembershipStatusEnum pendingApproval, String comments);

	Classroom getClassroomByIdentifier(Integer classroomId);

	Classroom createClassroom(Classroom classroom);

	void endMembership(Integer membershipId);

	void findRequestAndApprove(ClassroomMembershipStatusEnum status, Integer membershipId);

	List<ClassroomMembership> getMembershipRequests(Integer classroomId, Integer appUserId);

	List<Classroom> getUserMemberships(AppUser user);

	boolean isActiveMemberOf(Integer classroomId, Integer user);

    List<Classroom> searchClassrooms(int page, int loggedUserId,boolean onlyMyClasses);

	ClassroomMembership getMembership(Integer memberId);
}
