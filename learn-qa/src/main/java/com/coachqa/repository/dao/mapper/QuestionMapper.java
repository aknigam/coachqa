package com.coachqa.repository.dao.mapper;

import com.coachqa.entity.AppUser;
import com.coachqa.entity.Classroom;
import com.coachqa.entity.Question;
import com.coachqa.entity.RefSubject;
import com.coachqa.enums.PostTypeEnum;
import com.coachqa.enums.QuestionStatusEnum;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class QuestionMapper implements RowMapper<Question> {

	@Override
	public Question mapRow(ResultSet rs, int rowNum) throws SQLException 
	{
		Question q= new Question();
		q.setQuestionId(rs.getInt("questionid"));
		q.setPostTypeEnum(PostTypeEnum.getPostType(rs.getInt("posttype")));

		q.setContent(rs.getString("content"));
		q.setLastActiveDate(rs.getTimestamp("lastactivedate"));
		q.setPostDate(rs.getTimestamp("postdate"));
		q.setNoOfViews(rs.getInt("noofviews"));
		q.setRefQuestionStatusId(QuestionStatusEnum.from(rs.getInt("refquestionstatusid")) );
		q.setVotes(rs.getInt("votes"));
		q.setClassroomId(rs.getInt("classroomid"));
		Classroom c  = new Classroom();
		c.setClassroomId(rs.getInt("classroomid"));
		c.setClassName(rs.getString("ClassName"));
		q.setClassroom(c);

		RefSubject s = new RefSubject();
		s.setRefSubjectId(rs.getInt("refsubjectid"));
		s.setSubjectName(rs.getString("subjectname"));
		q.setSubject(s);
		
		AppUser postedBy = new AppUser();
		postedBy.setAppUserId(rs.getInt("postedby"));
		postedBy.setFirstName(rs.getString("firstname"));
		postedBy.setMiddleName(rs.getString("middlename"));
		postedBy.setLastName(rs.getString("lastName"));
		
		q.setPostedBy(postedBy);
		return q;
	}

}
