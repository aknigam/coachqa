package com.coachqa.service.impl;

import com.coachqa.entity.Classroom;
import com.coachqa.entity.ClassroomSettings;
import com.coachqa.entity.Question;
import com.coachqa.enums.QuestionRatingEnum;
import com.coachqa.exception.*;
import com.coachqa.repository.dao.QuestionDAO;
import com.coachqa.service.ClassroomService;
import com.coachqa.service.QuestionService;
import com.coachqa.service.listeners.question.EventPublisher;
import com.coachqa.ws.model.AnswerModel;
import notification.entity.ApplicationEvent;
import notification.entity.EventStage;
import notification.entity.EventType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import java.sql.Date;
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

	/*


	Lifecycle
		->QUESTION_POSTED - unapproved question posted
			- notify the admin for content approval
			if(approved){
				->POST_APPROVED
					: notification should go to
						- owner
						- people interested in the subject, tag etc
						- people belonging to the class if the they are interested
			}
			else{
				->POST_REJECTED :
					- notification should go to owner with reason
			}

	 */
	@Override
	public Question postQuestion(Integer userId, final Question question) {

		validateTags(question.getTags());

		if(isPrivateQuestionWithoutClassroom(question)){
			throw new QuestionPostException( ApplicationErrorCode.QUESTION_POST_PRIVATE, "Private question can only be posted to a valid classroom");
		}

		if(isNotMemberofProvidedClassroom(question, question.getPostedBy().getAppUserId())){
			throw new QuestionPostException( ApplicationErrorCode.QUESTION_POST_PRIVATE);
		}

		Question qstn = transactionTemplate.execute(new TransactionCallback<Question>() {
            @Override
            public Question doInTransaction(TransactionStatus transactionStatus) {
                return  questionDao.addQuestionWithTags(question);
            }
        });

		Integer questionId = qstn.getQuestionId();
		publishPostQuestionEvent(qstn);

		return qstn;
	}

	@Transactional
	@Override
	public void updateQuestion(Question updatedQuestion) {

		Question existingQuestion = questionDao.getQuestionById(updatedQuestion.getQuestionId());
		if(existingQuestion == null){
			throw new QAEntityNotFoundException(ApplicationErrorCode.ENTITY_NOT_FOUND, "Question does not exists - "+ updatedQuestion.getQuestionId());
		}


		if(alreadyAnswered(existingQuestion)){
			throw new NotAuthorisedToUpdateException(ApplicationErrorCode.NOT_AUTHORIZEDTO_UPDATE, "Question cannotbe edited after it is answered");
		}

		validateTags(updatedQuestion.getTags());


		if(isPrivateQuestionWithoutClassroom(updatedQuestion)){
			throw new QuestionPostException( ApplicationErrorCode.QUESTION_POST_PRIVATE, "Private question can only be posted to a valid classroom");
		}

		if(isNotMemberofProvidedClassroom(updatedQuestion, updatedQuestion.getPostedBy().getAppUserId())){
			throw new QuestionPostException( ApplicationErrorCode.QUESTION_POST_PRIVATE);
		}

		Question qstn = transactionTemplate.execute(new TransactionCallback<Question>() {
			@Override
			public Question doInTransaction(TransactionStatus transactionStatus) {
				return  questionDao.updateQuestion(updatedQuestion);
			}
		});


		publishPostQuestionEvent(qstn);


	}

	private boolean alreadyAnswered(Question existingQuestion) {
		return existingQuestion.getAnswers() != null && !existingQuestion.getAnswers().isEmpty();
	}

	private void publishPostQuestionEvent(Question qstn) {

		Integer questionId = qstn.getQuestionId();

		/*
		Check classroom settings whether approval is required or not
		 */
		if(isApprovalRequired(qstn)){
// stage 1 indicates approval pending
			eventPublisher.publishEvent(new ApplicationEvent<Integer>(EventType.QUESTION_POSTED, questionId, EventStage.STAGE_ONE));
		}
		else
		{
			// stage 1 indicates approval pending
			eventPublisher.publishEvent(new ApplicationEvent<Integer>(EventType.QUESTION_POSTED, questionId, EventStage.STAGE_TWO));
		}


	}

	private boolean isApprovalRequired(Question qstn) {
		if(qstn.isPublicQuestion()){
			return true;
		}
		ClassroomSettings cs = classroomService.getClassroomSettings(qstn.getClassroomId());

		return cs.isPostApprovalRequired();
	}

	private boolean isNotMemberofProvidedClassroom(Question question, Integer postedByUserId) {
		return !classroomService.isActiveMemberOf(question.getClassroomId(), postedByUserId);
	}

	private boolean isPrivateQuestionWithoutClassroom(Question question) {
		return !question.isPublicQuestion() && ( question.getClassroomId() == null );
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
		eventPublisher.publishEvent(new ApplicationEvent<Integer>(EventType.ANSWER_POSTED, answer.getQuestionId(),
				userId,new Date(System.currentTimeMillis()),
				new Date(System.currentTimeMillis()) ));

		return question;
	}





	@Override
	public Question getQuestionById(Integer questionId) {
		Question question = questionDao.getQuestionById(questionId);

		if(question == null){
			throw new QAEntityNotFoundException(ApplicationErrorCode.ENTITY_NOT_FOUND, "Question does not exists - "+ questionId);
		}
		return question;
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
		// todo
	}



	/**
	 * One possible implemenation of this method could be to get questionIds from the DB and then fetch each question
	 * from cache if present.
	 *
	 * @param tagId
	 * @return
	 */
	@Deprecated
	@Override
	public List<Question> getQuestionsByTag(int tagId) {
		return questionDao.getQuestionsByTag(tagId);
	}

	@Override
	public void rateQuestion(Integer appUserId, Integer questionId, QuestionRatingEnum rating) {
		// todo
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
		// todo
		return true;
	}

	@Override
	public void deleteQuestion(Integer userId, Integer questionId) {
		// todo
	}
	@Override
	public void searchQuestion() {
		// todo
	}
	@Override
	public void shareQuestionByEmail(Integer questionId) {
		// todo
	}


}
