package com.coachqa.repository.dao.impl;

import com.coachqa.entity.AppUser;
import com.coachqa.entity.Classroom;
import com.coachqa.enums.ClassroomMembershipStatusEnum;
import com.coachqa.exception.ClassroomAlreadyExistsException;
import com.coachqa.repository.dao.ClassroomDAO;
import com.coachqa.repository.dao.mybatis.mapper.ClassroomMyBatisMapper;
import com.coachqa.ws.model.ClassroomMembership;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
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
	public void endMembership(Integer membershipId) {

		classroomMapper.changeMembershipStatus(membershipId, ClassroomMembershipStatusEnum.NOT_MEMBER.getId());

	}


	@Override
	public void findRequestAndApprove(ClassroomMembershipStatusEnum status, Integer membershipId) {

		classroomMapper.changeMembershipStatus( membershipId , status.getId());


	}


	// TODO: 06/12/18 this method needs to be modified
	@Override
	public List<ClassroomMembership> getMembershipRequests(Integer classroomId, Integer appUserId, Integer page) {

		return  classroomMapper.getMembershipRequests(classroomId, appUserId, ClassroomMembershipStatusEnum
				.PENDING_APPROVAL.getId(), page*PAGE_SIZE);
	}

	@Override
	public List<Classroom> getUserMemberships(AppUser user) {
		return  classroomMapper.getUserClassrooms(user.getAppUserId());
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

	@Override
	public ClassroomMembership getMembership(Integer membershipId) {
		return classroomMapper.getMembershipDetails(membershipId);
	}

	@Override
	public List<Integer> getMembersList(Integer classroomId) {
		return classroomMapper.getMembersList(classroomId);
	}

	@Override
	public List<Integer> getAllContirbutors(Integer postId) {
		return classroomMapper.getAllContributors(postId);
	}

	@Override
	@Deprecated
	public List<ClassroomMembership> getPendingMembershipRequests(Integer approverId) {
		return classroomMapper.getPendingMembershipRequests(approverId);
	}


}
