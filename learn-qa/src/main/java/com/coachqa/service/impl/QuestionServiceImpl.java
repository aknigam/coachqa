package com.coachqa.service.impl;

import com.coachqa.enums.QuestionRatingEnum;
import com.coachqa.exception.AnswerPostException;
import com.coachqa.exception.ApplicationErrorCode;
import com.coachqa.exception.QuestionPostException;
import com.coachqa.exception.TagsRequiredForQuestionException;
import com.coachqa.service.ClassroomService;
import com.coachqa.service.listeners.question.EventPublisher;
import notification.entity.ApplicationEvent;
import notification.entity.EventType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.coachqa.entity.Question;
import com.coachqa.repository.dao.QuestionDAO;
import com.coachqa.service.QuestionService;
import com.coachqa.ws.model.AnswerModel;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService {

	private static Logger LOGGER = LoggerFactory.getLogger(QuestionServiceImpl.class);

	@Autowired
	private QuestionDAO questionDao;

	@Autowired
	private TransactionTemplate transactionTemplate;

	@Autowired
	private ClassroomService classroomService;

	@Autowired
	@Lazy
	private EventPublisher eventPublisher;

	@Override
	public Question postQuestion(Integer userId, final Question question) {

		validateTags(question.getTags());

		if(isPrivateQuestionWithoutClassroom(question)){
			throw new QuestionPostException( ApplicationErrorCode.QUESTION_POST_PRIVATE, "Private question can only be posted to a valid classroom");
		}

		if(isNotMemberofProvidedClassroom(question, question.getPostedBy().getAppUserId())){
			throw new QuestionPostException( ApplicationErrorCode.QUESTION_POST_PRIVATE);
		}

		Question qstn = null;
		try{
			qstn = transactionTemplate.execute(new TransactionCallback<Question>() {
				@Override
				public Question doInTransaction(TransactionStatus transactionStatus) {
					return  questionDao.addQuestionWithTags(question);
				}
			});
		}catch(Exception e){
			LOGGER.error("Unexpected excepted error occurred while trying to add a question");
			throw e;
		}

		Integer questionId = qstn.getQuestionId();
		eventPublisher.publishEvent(new ApplicationEvent(EventType.QUESTION_POSTED, questionId));
		return qstn;
	}

	private boolean isNotMemberofProvidedClassroom(Question question, Integer postedByUserId) {
		return question.getClassroom() != null && !classroomService.isMemberOf(question.getClassroom().getClassroomId(), question.getPostedBy().getAppUserId());
	}

	private boolean isPrivateQuestionWithoutClassroom(Question question) {
		return !question.isPublicQuestion() && ( question.getClassroom() == null );
	}

	private void validateTags(List<Integer> tags) {
		if(tags ==  null || tags.isEmpty()){
			// should we relax this rule ?
			throw new TagsRequiredForQuestionException(ApplicationErrorCode.TAGS_REQUIRED_FOR_QUESTION);
		}
	}


	@Override
	public Question postAnswer(Integer userId, AnswerModel answer) {

		Question question = questionDao.getQuestionById(answer.getQuestionId());

		if(isNotMemberofProvidedClassroom(question, userId)){
			throw new AnswerPostException(ApplicationErrorCode.ANSWER_PRIVATE_QUESTION);
		}

		transactionTemplate.execute(new TransactionCallbackWithoutResult() {
			@Override
			protected void doInTransactionWithoutResult(TransactionStatus transactionStatus) {
				questionDao.addAnswertoQuestion(answer);
			}
		});
		eventPublisher.publishEvent(new ApplicationEvent<Integer>(EventType.ANSWER_POSTED, answer.getQuestionId()));

		return question;
	}


	@Override
	@Transactional
	public void updateQuestion(Integer userId, Integer questionId, String questionContent) {

		eventPublisher.publishEvent(new ApplicationEvent(EventType.QUESTION_UPDATED, questionId));
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
		Is it right to increment the view every time the user sees it. Or should there be only one view per user?
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

	/**
	 * Following items can be specified in the criteria:
	 *
	 * 1. Subject
	 * 2. Classroom
	 * 3. Tags
	 * 4. Date range (last 1 month, Last 1 week etc)
	 * 5. Most viewed
	 * 6. Difficulty level
	 * 7. Rating
	 * 8. Unanswered questions
	 * 9. Most active questions
	 * 10. Questions posted by a user
	 * 11. Questions answered by a user
	 * 12. All public questions
	 *
	 * @param criteria
	 * @param noOfResults
     * @return
     */
	@Override
	public List<Question> findSimilarQuestions(Question criteria, int noOfResults) {

		return questionDao.findSimilarQuestions(criteria);
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


}
