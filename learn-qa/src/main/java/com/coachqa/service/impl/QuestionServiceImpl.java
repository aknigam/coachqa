package com.coachqa.service.impl;

import com.coachqa.enums.QuestionRatingEnum;
import com.coachqa.service.listeners.QuestionPostPublisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.coachqa.entity.Question;
import com.coachqa.repository.dao.QuestionDAO;
import com.coachqa.service.QuestionService;
import com.coachqa.ws.model.AnswerModel;
import com.coachqa.ws.model.QuestionModel;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@Component
public class QuestionServiceImpl implements QuestionService {

	@Autowired
	private QuestionDAO questionDao;

	private QuestionPostPublisher questionPostPublisher = new QuestionPostPublisher();

	@Override
	public Integer addQuestion(Integer userId, QuestionModel model) {

		Question question = questionDao.addQuestion(model);
		Integer questionId = question.getQuestionId();
		questionPostPublisher.questionPosted(questionId);
		return questionId;

	}

	@Override
	public Question submitAnswer(Integer userId, AnswerModel answer) {

		questionDao.addAnswertoQuestion(answer);
		questionPostPublisher.questionAnswered(answer.getQuestionId());
		return null;
	}


	@Override
	public void updateQuestion(Integer userId, Integer questionId, String questionContent) {
		questionPostPublisher.questionUpdated(questionId);
	}

	@Override
	public void searchQuestion() {


	}

	@Override
	public void shareQuestionByEmail(Integer questionId) {

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
	public List<Integer> findSimilarQuestions(Integer questionId, int noOfResults) {
		return Collections.emptyList();
	}

	@Override
	public void voteQuestion(Integer userId, Integer questionId, boolean upOrDown) {
		Map<Integer, Boolean> votedQuestions =  questionDao.getVotedQuestions(userId);

		if(votedQuestions.get(questionId)!= null)
		{

			if(votedQuestions.containsKey(questionId) && Boolean.compare(votedQuestions.get(questionId), upOrDown) == 0)
			{
				throw new RuntimeException("You have already voted this question");
			}
		}
		questionDao.vote(questionId, userId, upOrDown);
	}

	@Override
	public void voteAnswer(Integer userId, Integer answerId, boolean upOrDown) {
		Map<Integer, Boolean> votedAnswers =  questionDao.getVotedAnswers(userId);

		if(votedAnswers.get(answerId)!= null)
		{

			if(votedAnswers.containsKey(answerId) && Boolean.compare(votedAnswers.get(answerId), upOrDown) == 0)
			{
				throw new RuntimeException("You have already voted this answer");
			}
		}
		questionDao.vote(answerId, userId, upOrDown);
	}

	@Override
	public void requestionAnswerFrom() {

		
	}



	@Override
	public void rateQuestion(Integer appUserId, Integer questionId, QuestionRatingEnum rating) {

		if(!isAuthorizedToRateQuestion())
			return;

		
	}

	private boolean isAuthorizedToRateQuestion() {
		return false;
	}

	@Override
	public void deleteQuestion(Integer userId, Integer questionId) {

		
	}

}
