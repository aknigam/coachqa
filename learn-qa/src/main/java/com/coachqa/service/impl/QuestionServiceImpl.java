package com.coachqa.service.impl;

import com.coachqa.enums.QuestionRatingEnum;
import com.coachqa.exception.ApplicationErrorCode;
import com.coachqa.exception.TagsRequiredForQuestionException;
import com.coachqa.service.listeners.*;
import com.coachqa.service.listeners.question.EventPublisher;
import com.coachqa.service.listeners.question.ImageToTextQuestionConverterQuestionListener;
import com.coachqa.service.listeners.question.IndexQuestionListener;
import notification.NotificationService;
import notification.entity.ApplicationEvent;
import notification.entity.EventType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.coachqa.entity.Question;
import com.coachqa.repository.dao.QuestionDAO;
import com.coachqa.service.QuestionService;
import com.coachqa.ws.model.AnswerModel;
import com.coachqa.ws.model.QuestionModel;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService {

	private static Logger LOGGER = LoggerFactory.getLogger(QuestionServiceImpl.class);

	@Autowired
	private QuestionDAO questionDao;

	@Autowired
	private TransactionTemplate transactionTemplate;

	private ApplicationEventListener questionPostPublisher;

	@Autowired
	private NotificationService notificationService;

	@PostConstruct
	public void init(){

		List<ApplicationEventListener<Integer>> listeners = new ArrayList<>();

		listeners.add(new RetryingEventListener( new ImageToTextQuestionConverterQuestionListener(this)));
		listeners.add(new RetryingEventListener( new IndexQuestionListener()));
		questionPostPublisher = new EventPublisher(listeners);
	}

	@Override
	public Question addQuestion(Integer userId, final QuestionModel model) {

		validateTags(model.getTags());
		Question question = null;
		try{
			question = transactionTemplate.execute(new TransactionCallback<Question>() {
				@Override
				public Question doInTransaction(TransactionStatus transactionStatus) {
					return  questionDao.addQuestion(model);
				}
			});
		}catch(Exception e){
			LOGGER.error("Unexpected excepted error occurred while trying to add a question");
			throw e;
		}
		Integer questionId = question.getQuestionId();
		publishEvent(EventType.QUESTION_POSTED, questionId);

		return question;
	}

	private void validateTags(List<Integer> tags) {
		if(tags ==  null || tags.isEmpty()){
			throw new TagsRequiredForQuestionException(ApplicationErrorCode.TAGS_REQUIRED_FOR_QUESTION);
		}
	}


	@Override
	public Question submitAnswer(Integer userId, AnswerModel answer) {
		transactionTemplate.execute(new TransactionCallbackWithoutResult() {
			@Override
			protected void doInTransactionWithoutResult(TransactionStatus transactionStatus) {
				questionDao.addAnswertoQuestion(answer);
			}
		});

		publishEvent(EventType.QUESTION_ANSWERED, answer.getQuestionId());
		return questionDao.getQuestionById(answer.getQuestionId());
	}


	@Override
	@Transactional
	public void updateQuestion(Integer userId, Integer questionId, String questionContent) {
		publishEvent(EventType.QUESTION_UPDATED, questionId);
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

	/**
	 * One possible implemenation of this method could be to get questionIds from the DB and then fetch each question
	 * from cache if present.
	 *
	 * @param tagId
	 * @return
	 */
	@Override
	public List<Question> getQuestionsByTag(int tagId) {
		return questionDao.getQuestionsByTag(tagId);
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


	private void publishEvent(EventType eventType, Integer questionId) {
		LOGGER.info("Publishing event of type " + eventType.name() + " , event source id is " + questionId);

		ApplicationEvent<Integer> event = new ApplicationEvent(eventType, questionId);

		notificationService.notifyUsers(event);
	}

}
