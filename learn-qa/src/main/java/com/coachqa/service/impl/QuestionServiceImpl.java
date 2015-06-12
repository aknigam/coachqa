package com.coachqa.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.coachqa.entity.Question;
import com.coachqa.repository.dao.QuestionDAO;
import com.coachqa.service.QuestionService;
import com.coachqa.ws.model.AnswerModel;
import com.coachqa.ws.model.QuestionModel;

@Component
public class QuestionServiceImpl implements QuestionService {

	@Autowired
	private QuestionDAO questionDao; 
	 
	@Override
	public void postQuestion() {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateQuestion() {
		// TODO Auto-generated method stub

	}

	@Override
	public void searchQuestion() {
		// TODO Auto-generated method stub

	}

	@Override
	public void shareQuestionByEmail() {
		// TODO Auto-generated method stub

	}

	@Override
	public Question addQuestion(QuestionModel model) {
		return questionDao.addQuestion(model);
	}

	@Override
	public Question submitAnswer(AnswerModel answer) {
		// TODO Auto-generated method stub
		questionDao.addAnswertoQuestion(answer);
		return null;
	}

	@Override
	public Question getQuestionById(Integer questionId) {
		return questionDao.getQuestionById(questionId);
		
	}

	@Override
	public void updateStats(Question question) {
		questionDao.updateStats(question);		
	}

	@Override
	public void requestionAnswerFrom() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void rateQuestion() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteQuestion() {
		// TODO Auto-generated method stub
		
	}

}
