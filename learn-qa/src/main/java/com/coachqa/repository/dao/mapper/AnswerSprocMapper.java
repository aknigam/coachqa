package com.coachqa.repository.dao.mapper;

import com.coachqa.entity.Answer;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AnswerSprocMapper implements RowMapper<Answer> {

	@Override
	public Answer mapRow(ResultSet rs, int rowNum) throws SQLException 
	{
		Answer a = new Answer();
		a.setContent(rs.getString("content"));
		a.setAnsweredByUserId(rs.getInt("answeredbyuserId"));
		a.setAnswerId(rs.getInt("answerid"));
		a.setQuestionId(rs.getInt("questionid"));
		a.setVotes(rs.getInt("votes"));
		a.setApprovalStatus(rs.getBoolean("approvalstatus"));
		return a;
	}

}
