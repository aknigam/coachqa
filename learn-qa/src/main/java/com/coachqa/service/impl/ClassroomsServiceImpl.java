package com.coachqa.service.impl;

import com.coachqa.entity.AppUser;
import com.coachqa.enums.ClassroomMembershipStatusEnum;
import com.coachqa.exception.NotAuthorisedToViewMembershipRequestsException;
import com.coachqa.exception.NotAuthorizedToApprovemembershipRequest;
import com.coachqa.exception.NotAuthorizedtoExistClassroomException;


import com.coachqa.service.UserService;
import com.coachqa.service.listeners.ApplicationEventListener;
import com.coachqa.service.listeners.question.EventPublisher;
import com.coachqa.service.listeners.question.SimpleEventPublisher;
import com.coachqa.ws.model.ClassroomMembershipRequest;
import com.coachqa.ws.model.MembershipRequest;
import notification.NotificationService;
import notification.entity.ApplicationEvent;
import notification.entity.EventType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.coachqa.entity.Classroom;
import com.coachqa.repository.dao.ClassroomDAO;
import com.coachqa.service.ClassroomService;
import org.springframework.context.annotation.Lazy;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.util.Assert;

import javax.annotation.PostConstruct;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Service
public class ClassroomsServiceImpl implements ClassroomService{

	private static Logger LOGGER = LoggerFactory.getLogger(ClassroomsServiceImpl.class);

	@Autowired
	private ClassroomDAO classroomDAO;

	@Autowired
	private UserService userService;

	@Autowired
	private TransactionTemplate transactionTemplate;

	@Autowired
	@Lazy
	private EventPublisher<Integer> publisher;

	@PostConstruct
	public void init(){
		Assert.notNull(publisher , "Publisher cannot be null");
	}


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

		try{
			transactionTemplate.execute(new TransactionCallback<String>() {
				@Override
				public String doInTransaction(TransactionStatus transactionStatus) {
					classroomDAO.joinClassroom(appUserId, classroomId, ClassroomMembershipStatusEnum.PENDING_APPROVAL, comments);
					return "success";

				}
			});
		}catch(DuplicateKeyException e){
			LOGGER.warn("Ussr %d already requested for classroom %d membership", appUserId, classroomId);
		}catch (Exception e){
			LOGGER.error("Unexpected excepted error occurred while trying to add membership");
			throw e;
		}

		notifyAdministrator(classroomId);
	}

	private void notifyAdministrator(Integer classroomId) {
		ApplicationEvent<Integer> event = new ApplicationEvent(EventType.MEMBERSHIP_REQUEST, classroomId, new Date(System.currentTimeMillis()), new Date(System.currentTimeMillis()));
		publisher.publishEvent(event);
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

	@Override
	public boolean isMemberOf(Integer classroomId, int user) {
		return true;
	}

	@Override
	public List<Classroom> getUserMemberships(AppUser user) {
		return classroomDAO.getUserMemberships(user);
	}

	private boolean isRequestorAuthorized(Integer classOwnerId, Integer requestedByUserId, Integer memberId) {

		if(isRequesterClassroomOwner(classOwnerId, requestedByUserId)){
			return true;
		}
		if(isRequestedBySelf(requestedByUserId, memberId)){
			return true;
		}
		
		return false;
	}

	private boolean isRequestedBySelf(Integer requestedByUserId, Integer memberId) {
		return requestedByUserId.equals(memberId);
	}

	private boolean isRequesterClassroomOwner(Integer classOwnerId, Integer requestedByUserId) {
		return classOwnerId.equals(requestedByUserId);
	}


}
