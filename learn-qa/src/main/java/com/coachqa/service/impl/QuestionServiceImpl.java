package com.coachqa.service.impl;

import com.coachqa.enums.QuestionRatingEnum;
import com.coachqa.service.listeners.*;
import com.coachqa.service.listeners.question.PublishQuestionToQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.coachqa.entity.Question;
import com.coachqa.repository.dao.QuestionDAO;
import com.coachqa.service.QuestionService;
import com.coachqa.ws.model.AnswerModel;
import com.coachqa.ws.model.QuestionModel;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

@Component
public class QuestionServiceImpl implements QuestionService {

	@Autowired
	private QuestionDAO questionDao;

	private ApplicationEventListener questionPostPublisher = new PublishQuestionToQueue();

	@Override
	@Transactional
	public Question addQuestion(Integer userId, QuestionModel model) {

		/*
		Transaction is managed in this method. If the listener is invoked from here then it will make the listener work in the same DB transaction
		which is not expected. So, the publisher can simply add the new questionId in a queue and return. Listeners attached to the queue will do
		the processing in a separate thread.
		 */
		Question question = questionDao.addQuestion(model);
		Integer questionId = question.getQuestionId();

		// This is incorrect. Event should be published outside of the transaction boundaries. May be event publication can be done
		// using AOP advice. or use programmatic transaction management.
		publishEvent(ApplicationEventTypeEnum.QUESTION_POSTED, questionId);

		return question;
	}


	@Override
	@Transactional
	public Question submitAnswer(Integer userId, AnswerModel answer) {

		questionDao.addAnswertoQuestion(answer);
		publishEvent(ApplicationEventTypeEnum.QUESTION_ANSWERED, answer.getQuestionId());
		return questionDao.getQuestionById(answer.getQuestionId());
	}


	@Override
	@Transactional
	public void updateQuestion(Integer userId, Integer questionId, String questionContent) {
		publishEvent(ApplicationEventTypeEnum.QUESTION_UPDATED, questionId);
	}


	@Override
	public Question getQuestionById(Integer questionId) {
		return questionDao.getQuestionById(questionId);
	}

	@Override
	@Transactional
	public Question getQuestionByIdAndIncrementViewCount(Integer questionId) {

		Question question = questionDao.getQuestionById(questionId);

		/*
		Is it right to increment the view everytime the user sees it. Or should there be only one view per user?
		 */
		questionDao.incrementQuestionViews(questionId);
		question.setNoOfViews(question.getNoOfViews()+1);
		return question;
	}


	@Override
	public void requestionAnswerFrom(Integer userId, Integer questionId, List<String> users) {

	}

	@Override
	public List<Question> getQuestionsByTag() {
		return null;
	}

	@Override
	public void rateQuestion(Integer appUserId, Integer questionId, QuestionRatingEnum rating) {

		if(!isAuthorizedToRateQuestion())
			return;
	}

	@Override
	public List<Integer> findSimilarQuestions(Integer questionId, int noOfResults) {
		return Collections.emptyList();
	}

	private boolean isAuthorizedToRateQuestion() {
		return true;
	}

	@Override
	public void deleteQuestion(Integer userId, Integer questionId) {}
	@Override
	public void searchQuestion() {}
	@Override
	public void shareQuestionByEmail(Integer questionId) {}


	private void publishEvent(ApplicationEventTypeEnum eventType, Integer questionId) {
		// questionPostPublisher.onEvent(new QuestionEvent(questionId,eventType ));
	}

}
