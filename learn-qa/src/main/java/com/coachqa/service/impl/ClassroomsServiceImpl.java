package com.coachqa.service.impl;

import com.coachqa.entity.AppUser;
import com.coachqa.enums.ClassroomMembershipStatusEnum;
import com.coachqa.exception.NotAuthorisedToViewMembershipRequestsException;
import com.coachqa.exception.NotAuthorizedToApprovemembershipRequest;
import com.coachqa.exception.NotAuthorizedtoExistClassroomException;


import com.coachqa.service.UserService;
import com.coachqa.ws.model.ClassroomMembershipRequest;
import com.coachqa.ws.model.MembershipRequest;
import notification.NotificationService;
import notification.entity.ApplicationEvent;
import notification.entity.EventType;
import notification.entity.SimpleKeyedResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.coachqa.entity.Classroom;
import com.coachqa.repository.dao.ClassroomDAO;
import com.coachqa.service.ClassroomService;
import org.springframework.stereotype.Service;

import java.sql.Date;

@Service
public class ClassroomsServiceImpl implements ClassroomService{

	private static Logger LOGGER = LoggerFactory.getLogger(ClassroomsServiceImpl.class);

	@Autowired
	private ClassroomDAO classroomDAO;

	@Autowired
	private UserService userService;

	private NotificationService notificationService;

	@Override
	public Classroom getClassroom(Integer classroomId) {
		return classroomDAO.getClassroomByIdentifier(classroomId);
	}


	/**
	 * Joining a classroom may require approval from the classroom owner.
	 * To process this request we will notify the admin to approve the request.
	 * On approval the membership will be activated.
	 */
	@Override
	public void requestClassroomMembership(Integer appUserId, Integer classroomId, String comments) {

		classroomDAO.joinClassroom(appUserId, classroomId, ClassroomMembershipStatusEnum.PENDING_APPROVAL, comments);
		notifyAdministrator(classroomId);
	}

	private void notifyAdministrator(Integer classroomId) {
		ApplicationEvent event = new ApplicationEvent(EventType.MEMBERSHIP_REQUEST, new SimpleKeyedResource(classroomId, null), new Date(System.currentTimeMillis()), new Date(System.currentTimeMillis()));
		notificationService.notifyUsers(event);
	}

	@Override
	public Classroom createClassroom(Classroom classroom) {

		Classroom addedClassroom =  classroomDAO.createClassroom(classroom);
		return addedClassroom;
	}

	@Override
	public Classroom getClassroomByName(String classname) {
		return null;
	}

	@Override
	public void leaveClassroom(Integer classroomId, Integer requestedByUserId, Integer memberId, String comments) {

		Classroom classroom = classroomDAO.getClassroomByIdentifier(classroomId);
		if(isRequestorAuthorized(classroom.getClassOwner().getAppUserId(), requestedByUserId, memberId)){
			classroomDAO.endMembership(classroomId, memberId, comments);
			notifyUser(userService.getUserDetails(memberId), true, null);
		}
		throw new NotAuthorizedtoExistClassroomException();

	}

	@Override
	public void processJoinRequest(AppUser approver, ClassroomMembershipRequest membershipRequests) {

		Classroom classroom = classroomDAO.getClassroomByIdentifier(membershipRequests.getClassroomId());
		if(!classroom.getClassOwner().getAppUserId().equals(approver.getAppUserId())){
			LOGGER.error(String.format("%s is not authorized to approved membership requests for classroom %s", approver.getEmail(), classroom.getClassName()));
			throw new NotAuthorizedToApprovemembershipRequest(approver, classroom);
		}
		boolean isApproved= membershipRequests.isApprove();
		for (MembershipRequest request: membershipRequests.getRequests()) {
			classroomDAO.findRequestAndApprove(isApproved, classroom.getClassroomId(), request.getUser().getAppUserId(), membershipRequests.getComments());

			notifyUser(request.getUser(), isApproved, classroom);
		}


		// notification has to be sent to the requester.
	}

	private void notifyUser(AppUser user, boolean isApproved, Classroom classroom) {
		// send email or other type of notification.
	}

	@Override
	public ClassroomMembershipRequest getMemberShipRequests(AppUser user, Integer classroomId) {
		Classroom classroom = classroomDAO.getClassroomByIdentifier(classroomId);
		if(!classroom.getClassOwner().getAppUserId().equals(user.getAppUserId())){
			throw new NotAuthorisedToViewMembershipRequestsException(user, classroom);
		}
		return classroomDAO.getMembershipRequests(classroomId);
	}

	private boolean isRequestorAuthorized(Integer classOwnerId, Integer requestedByUserId, Integer memberId) {
		if(classOwnerId.equals(requestedByUserId) || requestedByUserId.equals(memberId)){
			return true;
		}
		return false;
	}


}
