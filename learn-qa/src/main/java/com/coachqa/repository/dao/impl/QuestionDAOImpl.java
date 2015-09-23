package com.coachqa.repository.dao.impl;

import javax.sql.DataSource;

import com.coachqa.entity.QuestionVote;
import org.joda.time.DateTime;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import com.coachqa.entity.Question;
import com.coachqa.repository.dao.QuestionDAO;
import com.coachqa.repository.dao.sp.AnswerAddSproc;
import com.coachqa.repository.dao.sp.QuestionAddSproc;
import com.coachqa.repository.dao.sp.QuestionGetSproc;
import com.coachqa.ws.model.AnswerModel;
import com.coachqa.ws.model.QuestionModel;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

@Component
public class QuestionDAOImpl extends BaseDao implements QuestionDAO, InitializingBean {

	
	private QuestionAddSproc questionAddSproc;
	
	private QuestionGetSproc questionGetSproc;
	
	private QuestionGetSproc questionUpdateStatsSproc;
	
	private AnswerAddSproc answerAddSproc;
	
	@Override
	public void afterPropertiesSet() throws Exception {
		DataSource dataSource = getDataSource();
		questionAddSproc = new QuestionAddSproc(dataSource);
		questionGetSproc = new QuestionGetSproc(dataSource);
		answerAddSproc = new AnswerAddSproc(dataSource);
	}

    @CachePut(value="questions", key="#result.questionId")
	@Override
	public Question addQuestion(QuestionModel question) {

		Question addedQuestion = questionAddSproc.addQuestion(question);
		int questionId =  addedQuestion.getQuestionId();

		for (Integer tagId : question.getTags())
			tagQuestion(questionId, tagId);

		return addedQuestion;
	}
	private static String tagQuestionInsertQuery = "Insert into questiontag  (questionId, tagId) values (?,?)";

	private void tagQuestion(int questionId, Integer tagId) {
		jdbcTemplate.update(tagQuestionInsertQuery , new Integer[]{questionId, tagId});
	}

	@Cacheable(value="questions", key="#questionId")
	@Override
	public Question getQuestionById(Integer questionId) {
		return questionGetSproc.getQuestionById(questionId);
	}

	@Override
	@CacheEvict(value="questions", key="#answer.questionId")
	public void addAnswertoQuestion(AnswerModel answer) {
		answerAddSproc.addAnswer(answer);
	}

	@Override
	public void updateStats(Question question) {
		// TODO Auto-generated method stub
		
	}


	private String incrementQuestionViewsQuery =  "Update question set NoOfViews = NoOfViews + 1 where questionId =  ?";

	private String incrementQuestionVotesQuery =  "Update Question set Votes = Votes + ? where questionId = ?";

	@Override
	public void incrementQuestionViews(Integer questionId) {

		jdbcTemplate.update(incrementQuestionViewsQuery , new Integer[]{questionId});

	}



}
