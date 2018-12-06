package com.coachqa.service.impl;

import com.coachqa.entity.AppUser;
import com.coachqa.entity.Classroom;
import com.coachqa.entity.ClassroomSettings;
import com.coachqa.enums.ClassroomMembershipStatusEnum;
import com.coachqa.exception.ApplicationErrorCode;
import com.coachqa.exception.ClassroomNotExistsException;
import com.coachqa.exception.NotAuthorisedToApproveException;
import com.coachqa.exception.NotAuthorisedToViewMembershipRequestsException;
import com.coachqa.exception.NotAuthorizedtoExistClassroomException;
import com.coachqa.repository.dao.ClassroomDAO;
import com.coachqa.service.ClassroomService;
import com.coachqa.service.UserService;
import com.coachqa.service.listeners.question.EventPublisher;
import com.coachqa.ws.model.ClassroomMembership;
import com.sun.javaws.exceptions.InvalidArgumentException;
import notification.entity.ApplicationEvent;
import notification.entity.EventType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.util.Assert;

import javax.annotation.PostConstruct;
import java.sql.Date;
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
	private EventPublisher publisher;

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
					classroomDAO.joinClassroom(appUserId, classroomId, ClassroomMembershipStatusEnum
							.PENDING_APPROVAL, comments);
					return "success";

				}
			});
		}catch(DuplicateKeyException e){
			LOGGER.warn("User %d already requested for classroom %d membership", appUserId, classroomId, e);
		}catch (Exception e){
			LOGGER.error("Unexpected excepted error occurred while trying to add membership");
			throw e;
		}

		notifyAdministrator(classroomId, appUserId);
	}

	private void notifyAdministrator(Integer classroomId, Integer userId) {
		ApplicationEvent event = new ApplicationEvent(EventType.MEMBERSHIP_REQUEST, classroomId, userId,
				new Date(System.currentTimeMillis()), new Date(System.currentTimeMillis()));
		publisher.publishEvent(event);
	}

	@Transactional
	@Override
	public Classroom createClassroom(Classroom classroom) {

		Classroom addedClassroom =  classroomDAO.createClassroom(classroom);
		return addedClassroom;
	}

	@Override
	public Classroom getClassroomByName(String classname) {
		// todo
		return null;
	}

	@Override
	public void leaveClassroom(int requestedByUserId, Integer membershipId) {

		ClassroomMembership membership =  classroomDAO.getMembership(membershipId);
		Classroom classroom = classroomDAO.getClassroomByIdentifier(membershipId);
		if(isRequestorAuthorized(classroom.getClassOwner().getAppUserId(), requestedByUserId, membership.getMemberId())){
			classroomDAO.endMembership(membershipId);
			// TODO: 04/12/18 do we need to send a notification here
//			notifyUser(userService.getUserDetails(memberId), true, null);
		}
		throw new NotAuthorizedtoExistClassroomException();

	}

	@Override
	public void processJoinRequest(AppUser approver, List<Integer> membershipRequests, boolean isApprove) {


		for(Integer mId: membershipRequests) {
			ClassroomMembership cm = classroomDAO.getMembership(mId);
			Classroom classroom = classroomDAO.getClassroomByIdentifier(cm.getClassroomId());
			validateApprover(classroom.getClassroomId(), approver.getAppUserId());


			classroomDAO.findRequestAndApprove(isApprove ? ClassroomMembershipStatusEnum.ACTIVE :
					ClassroomMembershipStatusEnum.REJECTED, mId);

			notifyUserForMembershipApproval(classroom.getClassroomId(), cm.getMemberId(), isApprove);

		}

	}



	protected void validateApprover(Integer classroomId, Integer approverUserId) throws NotAuthorisedToApproveException {


		Classroom classroom = classroomDAO.getClassroomByIdentifier(classroomId);
		if(!classroom.getClassOwner().getAppUserId().equals(approverUserId)) {
			throw new NotAuthorisedToApproveException(ApplicationErrorCode.NOT_AUTHORIZEDTO_APPROVE, "Only admin can approve the request");
		}

//        throw new InvalidEventForApprovalException(ApplicationErrorCode.IN_VALID_EVENT, "Invalid event type");

	}

	protected void notifyUserForMembershipApproval(Integer requesterUserId, Integer classroomId, boolean isApproved) {

		// the new event should be a notification to the person who requested membership
		ApplicationEvent approvedEvent = new ApplicationEvent(isApproved ? EventType.MEMBERSHIP_APPROVED : EventType.MEMBERSHIP_REJECTED,
				classroomId, requesterUserId,
				new Date(System.currentTimeMillis()),
				new Date(System.currentTimeMillis()));

		publisher.publishEvent(approvedEvent);
	}



	@Override
	public List<ClassroomMembership> getMemberShipRequests(AppUser user, Integer classroomId) {
		if(classroomId != null) {
			Classroom classroom = classroomDAO.getClassroomByIdentifier(classroomId);
			if(classroom == null) {
				throw new ClassroomNotExistsException(ApplicationErrorCode.CLASSROOM_NOT_FOUND, "Invalid classroom " +
						"identifier provided");
			}
			if (!classroom.getClassOwner().getAppUserId().equals(user.getAppUserId())) {
				throw new NotAuthorisedToViewMembershipRequestsException(user, classroom);
			}

		}

		return  classroomDAO.getMembershipRequests(classroomId, user.getAppUserId());
	}

	@Override
	public boolean isActiveMemberOf(Integer classroomId, int user) {
		return  classroomDAO.isActiveMemberOf(classroomId, user);

	}

	@Override
	public List<Classroom> getUserMemberships(AppUser user) {
		List<Classroom> classrooms = classroomDAO.getUserMemberships(user);
		return classrooms;
	}


	@Override
	public ClassroomSettings getClassroomSettings(Integer classroomId) {
		return new ClassroomSettings();
	}

	@Override
	public List<Classroom> searchClassrooms(Integer userId, Integer page, boolean onlyLoginUserClassrooms) {
		return classroomDAO.searchClassrooms(page, userId, onlyLoginUserClassrooms);
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
