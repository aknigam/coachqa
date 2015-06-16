package com.coachqa.repository.dao.impl;

import javax.sql.DataSource;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import com.coachqa.entity.Question;
import com.coachqa.repository.dao.QuestionDAO;
import com.coachqa.repository.dao.sp.AnswerAddSproc;
import com.coachqa.repository.dao.sp.QuestionAddSproc;
import com.coachqa.repository.dao.sp.QuestionGetSproc;
import com.coachqa.ws.model.AnswerModel;
import com.coachqa.ws.model.QuestionModel;

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

    @CachePut(value="questions", key="questionId")
	@Override
	public Question addQuestion(QuestionModel model) {

		return questionAddSproc.addQuestion(model);

	}

    @Cacheable(value="questions", key="questionId")
	@Override
	public Question getQuestionById(Integer questionId) {
		return questionGetSproc.getQuestionById(questionId);
	}

	@Override
	public void addAnswertoQuestion(AnswerModel answer) {
		answerAddSproc.addAnswer(answer);
	}

	@Override
	public void updateStats(Question question) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Map<Integer, Boolean> getVotedQuestions(Integer userId) {
		return getUserVotedQuestions(userId);
	}
	private Map<Integer, Map<Integer, Boolean>> allUserVotedQuestions = new HashMap<>();
	private Map<Integer, Map<Integer, Boolean>> allUserVotedAnswers = new HashMap<>();

	@Override
	public void vote(Integer questionId, Integer userId, boolean upOrDown) {
		Map<Integer, Boolean> userVotedQuestions =  getUserVotedQuestions(userId);
		userVotedQuestions.put(questionId, upOrDown);

	}

	@Override
	public Map<Integer, Boolean> getVotedAnswers(Integer userId) {
		return getUserVotedAnswers(userId);
	}

	private Map<Integer,Boolean> getUserVotedAnswers(Integer userId) {
		Map<Integer, Boolean> userVotedAnswers = allUserVotedAnswers.get(userId);
		if(userVotedAnswers == null) {
			userVotedAnswers = new HashMap<>();
			allUserVotedAnswers.put(userId, userVotedAnswers);
		}
		return userVotedAnswers;
	}

	private Map<Integer,Boolean> getUserVotedQuestions(Integer userId) {
		Map<Integer, Boolean> userVotedQuestions = allUserVotedQuestions.get(userId);
		if(userVotedQuestions == null) {
			userVotedQuestions = new HashMap<>();
			allUserVotedQuestions.put(userId, userVotedQuestions);
		}
		return userVotedQuestions;
	}


}
