package com.coachqa.repository.dao.mapper;

import com.coachqa.entity.AppUser;
import com.coachqa.entity.Question;
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
		q.setQuestionId(rs.getInt("QuestionId"));
		q.setPostTypeEnum(PostTypeEnum.getPostType(rs.getInt("postType")));
		q.setTitle(rs.getString("Title"));
		q.setContent(rs.getString("Content"));
		q.setPublicQuestion(rs.getBoolean("IsPublic"));
		q.setLastActiveDate(rs.getDate("LastActiveDate"));
		q.setPostDate(rs.getDate("PostDate"));
		q.setNoOfViews(rs.getInt("NoOfViews"));
		q.setRefQuestionStatusId(QuestionStatusEnum.from(rs.getInt("RefQuestionStatusId")) );
		q.setVotes(rs.getInt("Votes"));
		
		AppUser postedBy = new AppUser();
		postedBy.setAppUserId(rs.getInt("PostedBy"));
		postedBy.setFirstName(rs.getString("Firstname"));
		postedBy.setMiddleName(rs.getString("middleName"));
		postedBy.setLastName(rs.getString("lastName"));
		
		q.setPostedBy(postedBy);
		return q;
	}

}
