package com.coachqa.repository.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.coachqa.entity.Answer;

public class AnswerMapper implements RowMapper<Answer> {

	@Override
	public Answer mapRow(ResultSet rs, int rowNum) throws SQLException 
	{
		Answer a = new Answer();
		a.setContent(rs.getString("content"));
		a.setAnsweredByUserId(rs.getInt("AnsweredByUserId"));
		a.setAnswerId(rs.getInt("AnswerId"));
		a.setQuestionId(rs.getInt("QuestionId"));
		a.setVotes(rs.getInt("Votes"));
		return a;
	}

}