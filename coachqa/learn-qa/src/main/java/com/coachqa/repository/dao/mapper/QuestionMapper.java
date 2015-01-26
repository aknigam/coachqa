package com.coachqa.repository.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.coachqa.entity.AppUser;
import com.coachqa.entity.Question;

public class QuestionMapper implements RowMapper<Question> {

	@Override
	public Question mapRow(ResultSet rs, int rowNum) throws SQLException 
	{
		Question q= new Question();
		q.setQuestionId(rs.getInt("QuestionId"));
		q.setTitle(rs.getString("Title"));
		q.setContent(rs.getString("Content"));
		q.setIsPublic(rs.getBoolean("IsPublic"));
		q.setLastActiveDate(rs.getDate("LastActiveDate"));
		q.setPostDate(rs.getDate("PostDate"));
		q.setNoOfViews(rs.getInt("NoOfViews"));
		q.setRefQuestionStatusId(rs.getInt("RefQuestionStatusId"));
		
		AppUser postedBy = new AppUser();
		postedBy.setAppUserId(rs.getInt("PostedBy"));
		postedBy.setFirstName(rs.getString("Firstname"));
		postedBy.setMiddleName(rs.getString("middleName"));
		postedBy.setLastName(rs.getString("lastName"));
		
		q.setPostedBy(postedBy);
		return q;
	}

}
