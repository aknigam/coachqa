package com.coachqa.repository.dao.impl;

import com.coachqa.entity.AppUser;
import com.coachqa.entity.Classroom;
import com.coachqa.enums.ClassroomMembershipStatusEnum;
import com.coachqa.exception.ApplicationErrorCode;
import com.coachqa.exception.ClassroomAlreadyExistsException;
import com.coachqa.exception.NotAClassroomMemberException;
import com.coachqa.repository.dao.ClassroomDAO;
import com.coachqa.repository.dao.mybatis.mapper.ClassroomMyBatisMapper;

import com.coachqa.ws.model.ClassroomMembershipRequest;
import com.coachqa.ws.model.MembershipRequest;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

@Repository
public class ClassroomDAOImpl extends BaseDao implements ClassroomDAO {

	private static Logger LOGGER = LoggerFactory.getLogger(ClassroomDAOImpl.class);


	@Autowired
	private ClassroomMyBatisMapper classroomMapper;


	// TODO: 26/01/18 move these queries to mapper

	private static String addMembershipQuery = "Insert into classroommember (appuserid, classroomid, status, comments, " +
			"membershiprequestdate, membershipstartdate, membershipexpirartiondate) values (?, ?, ?, ?, ?, ?, ?)";

	private static String addClassroomQuery = "INSERT INTO classroom(classowner,classname,ispublic,description, " +
			"lastupdatedate, subjectId )" +
			" VALUES(?, ?, ?, ?, ?, ?)";

	private static String endMembershipQuery ="update classroommember set status = ? , comments = ? where " +
			"classroomid = ? and appUserId = ?";

	private static String approveMembershipRequestQuery =
			"Update classroommember set status = ?, comments = ? , membershipstartdate = ? , " +
					" membershipexpirartiondate= ? " +
					" where classroomid = ? and appUserId = ? and status = ? ";
	private static String denyMembershipRequestQuery =  "Update classroommember set status = ?, comments = ? " +
			" where classroomid = ? and appUserId = ? and status = ? ";


	private static String getMembershipRequestsQuery  = "select c.classroomid, c.className, au.email,  au.appUserId, " +
			"au.firstname, au.middlename, au.lastName " +
			" , cm.membershiprequestdate " +
			" , cm.comments" +
			" from classroommember cm " +
			" join appUser au on au.appuserId =  cm.appuserid " +
			" join classroom c on c.classroomid = cm.classroomid " +
			" where cm.status = ?  and c.classroomid = ?";


	@Override
	public void joinClassroom(Integer appUserId, Integer classroomId, ClassroomMembershipStatusEnum pendingApproval, String comments) {

		DateTime expirationDate =  new DateTime(System.currentTimeMillis()).plusYears(1);
		jdbcTemplate.update(addMembershipQuery, new Object[]{appUserId
				, classroomId
				, pendingApproval.getId()
				, comments
				, new Date(System.currentTimeMillis())
				, new Date(System.currentTimeMillis())
				, new Date(expirationDate.toDate().getTime())});

	}

	@Override
	public Classroom getClassroomByIdentifier(Integer classroomId) {

		return  classroomMapper.getClassroomByIdentifier(classroomId);

	}


	@Override
	public Classroom createClassroom(final Classroom classroom) {
		KeyHolder holder = new GeneratedKeyHolder();
		try {
			jdbcTemplate.update(new PreparedStatementCreator() {
				@Override
				public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
					PreparedStatement ps = connection.prepareStatement(addClassroomQuery, Statement.RETURN_GENERATED_KEYS);
					ps.setInt(1, classroom.getClassOwner().getAppUserId());
					ps.setString(2, classroom.getClassName());
					ps.setBoolean(3, classroom.isIsPublic());
					ps.setString(4, classroom.getDescription());
					Date now = new Date(System.currentTimeMillis());
					ps.setDate(5, now);
					ps.setInt(6, classroom.getSubject().getRefSubjectId());

					return ps;
				}
			}, holder);
		}catch(DuplicateKeyException dke){
			throw new ClassroomAlreadyExistsException(classroom.getClassName());
		}

		Integer classroomId = holder.getKey().intValue();
		classroom.setClassroomId(classroomId);

		return classroom;
	}


	@Override
	public void endMembership(Integer classroomId, Integer memberId, String comments) {

		int rowsDeletedOrUpdated = jdbcTemplate.update(endMembershipQuery, new Object[]{ClassroomMembershipStatusEnum.EXPIRED.getId(), comments, classroomId, memberId});
		if(rowsDeletedOrUpdated == 0){
			throw new NotAClassroomMemberException(ApplicationErrorCode.INVALID_MEMBERSHIP, String.format( "%d is not a meber of classroom with id %d", memberId, classroomId));
		}
	}


	@Override
	public void findRequestAndApprove(boolean approve, Integer classroomId, Integer userId, String comments) {

		int updatedRows = 0;
		if(!approve){
			LOGGER.debug(String.format("Approving the join request"));
			jdbcTemplate.update(denyMembershipRequestQuery, new Object[]{
					ClassroomMembershipStatusEnum.REJECTED.getId()
					, comments
					, classroomId
					, userId
					, ClassroomMembershipStatusEnum.PENDING_APPROVAL.getId()});
		}
		else{
			LOGGER.debug(String.format("Rejecting the join request"));
			jdbcTemplate.update(approveMembershipRequestQuery, new Object[]{
					ClassroomMembershipStatusEnum.ACTIVE.getId()
					,comments, new Date(DateTime.now().getMillis())
					, new Date(DateTime.now().plusYears(20).getMillis())
					, classroomId
					, userId
					, ClassroomMembershipStatusEnum.PENDING_APPROVAL.getId()});
		}


		if(updatedRows >0){
			LOGGER.debug(String.format("membership approval/denial successfully completed for classroomid %d and user %d ", classroomId, userId));
		}
		else{
			LOGGER.warn(String.format("Membership approval request not found for classroomid %d and user %d ", classroomId, userId));
		}

	}


	@Override
	public ClassroomMembershipRequest getMembershipRequests(Integer classroomId) {
		
		ClassroomMembershipRequest classroomMembershipRequest = new ClassroomMembershipRequest(classroomId);
		List<MembershipRequest> requests  =  jdbcTemplate.query(getMembershipRequestsQuery, new Integer[]{ClassroomMembershipStatusEnum.PENDING_APPROVAL.getId(), classroomId}, new RowMapper<MembershipRequest>() {
			@Override
			public MembershipRequest mapRow(ResultSet rs, int i) throws SQLException {
				int appUserId = rs.getInt("appUserId");
				int classroomId = rs.getInt("classroomid");
				String classname = rs.getString("className");
				String email = rs.getString("email");
				String firstName = rs.getString("firstname");
				String middleName = rs.getString("middlename");
				String lastName = rs.getString("lastName");
				DateTime requestDate = new DateTime(rs.getDate("membershiprequestdate"));
				String comments = rs.getString("comments");

				AppUser user = new AppUser(appUserId, email, firstName, middleName, lastName);

				return new MembershipRequest(user, requestDate, comments);
			}
		});
		
		classroomMembershipRequest.setRequests( requests );
		return classroomMembershipRequest;
	}

	@Override
	public List<Classroom> getUserMemberships(AppUser user) {
		return  classroomMapper.getUserClassrooms(user.getAppUserId());
//		return Arrays.asList(new Classroom[]{new Classroom(6, "Physics class")});
	}

	@Override
	public boolean isActiveMemberOf(Integer classroomId, Integer user) {
		Integer ClassroomMemberId = classroomMapper.getMembership(classroomId, user);
		return ClassroomMemberId != null;
	}

	@Override
	public List<Classroom> searchClassrooms(int page, int loggedUserId, boolean onlyMyClasses) {
		page = page * 10;
		List<Classroom> classrooms = classroomMapper.searchClassrooms(page, loggedUserId, onlyMyClasses);
		return classrooms;
	}

}
