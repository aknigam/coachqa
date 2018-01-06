package com.coachqa.repository.dao.impl;

import com.coachqa.entity.AppUser;
import com.coachqa.entity.Classroom;
import com.coachqa.enums.ClassroomMembershipStatusEnum;
import com.coachqa.exception.ApplicationErrorCode;
import com.coachqa.exception.ClassroomAlreadyExistsException;
import com.coachqa.exception.NotAClassroomMemberException;
import com.coachqa.repository.dao.ClassroomDAO;
import com.coachqa.repository.dao.mapper.ClassRoomMapper;
import com.coachqa.repository.dao.mybatis.mapper.ClassroomMyBatisMapper;
import com.coachqa.repository.dao.sp.ClassroomGetByIdSproc;
import com.coachqa.ws.model.ClassroomMembershipRequest;
import com.coachqa.ws.model.MembershipRequest;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.Arrays;
import java.util.List;

@Repository
public class ClassroomDAOImpl extends BaseDao implements InitializingBean, ClassroomDAO {

	private static Logger LOGGER = LoggerFactory.getLogger(ClassroomDAOImpl.class);

	private ClassroomGetByIdSproc classroomGetByIdSproc;

	@Autowired
	private ClassroomMyBatisMapper classroomMapper;


	@Override
	public void afterPropertiesSet() throws Exception {
		classroomGetByIdSproc = new ClassroomGetByIdSproc(getDataSource());
	}

	private static String addMembershipQuery = "Insert into classroomMember (AppUserId, ClassroomId, Status, Comments, " +
			"MembershipRequestDate, MembershipStartDate, MembershipExpirartionDate) values (?, ?, ?, ?, ?, ?, ?)";

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
		return classroomGetByIdSproc.getClassroomByIdentifier(classroomId);
	}

	private static String addClassroomQuery = "INSERT INTO classroom(ClassOwner,ClassName,IsPublic,description, LastUpdateDate)" +
			" VALUES(?, ?, ?, ?, ?)";
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

	private static String endMembershipQuery ="update classroomMember set status = ? , comments = ? where classroomId = ? and appUserId = ?";
	@Override
	public void endMembership(Integer classroomId, Integer memberId, String comments) {

		int rowsDeletedOrUpdated = jdbcTemplate.update(endMembershipQuery, new Object[]{ClassroomMembershipStatusEnum.EXPIRED.getId(), comments, classroomId, memberId});
		if(rowsDeletedOrUpdated == 0){
			throw new NotAClassroomMemberException(ApplicationErrorCode.INVALID_MEMBERSHIP, String.format( "%d is not a meber of classroom with id %d", memberId, classroomId));
		}
	}

	private static String approveMembershipRequestQuery =
			"Update classroomMember set status = ?, comments = ? , MembershipStartDate = ? , " +
			" MembershipExpirartionDate= ? " +
			" where classroomId = ? and appUserId = ? and status = ? ";
	private static String denyMembershipRequestQuery =  "Update classroomMember set status = ?, comments = ? where classroomId = ? and appUserId = ? and status = ? ";
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
			LOGGER.debug(String.format("membership approval/denial successfully completed for classroomId %d and user %d ", classroomId, userId));
		}
		else{
			LOGGER.warn(String.format("Membership approval request not found for classroomId %d and user %d ", classroomId, userId));
		}

	}

	private static String getMembershipRequestsQuery  = "select c.classroomId, c.className, au.email,  au.appUserId, au.FirstName, au.MiddleName, au.lastName " +
			" , cm.MembershipRequestDate " +
			" , cm.comments" +
			" from classroomMember cm " +
			" join appUser au on au.appuserId =  cm.AppUserId " +
			" join Classroom c on c.classroomId = cm.classroomId " +
			" where cm.status = ?  and c.classroomId = ?";

	@Override
	public ClassroomMembershipRequest getMembershipRequests(Integer classroomId) {
		
		ClassroomMembershipRequest classroomMembershipRequest = new ClassroomMembershipRequest(classroomId);
		List<MembershipRequest> requests  =  jdbcTemplate.query(getMembershipRequestsQuery, new Integer[]{ClassroomMembershipStatusEnum.PENDING_APPROVAL.getId(), classroomId}, new RowMapper<MembershipRequest>() {
			@Override
			public MembershipRequest mapRow(ResultSet rs, int i) throws SQLException {
				int appUserId = rs.getInt("appUserId");
				int classroomId = rs.getInt("classroomId");
				String classname = rs.getString("className");
				String email = rs.getString("email");
				String firstName = rs.getString("FirstName");
				String middleName = rs.getString("MiddleName");
				String lastName = rs.getString("lastName");
				DateTime requestDate = new DateTime(rs.getDate("MembershipRequestDate"));
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

}
