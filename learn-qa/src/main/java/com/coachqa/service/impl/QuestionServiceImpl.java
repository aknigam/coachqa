package com.coachqa.service.impl;

import com.coachqa.entity.Account;
import com.coachqa.entity.Answer;
import com.coachqa.entity.AppUser;
import com.coachqa.entity.Question;
import com.coachqa.entity.Tag;
import com.coachqa.enums.QuestionRatingEnum;
import com.coachqa.exception.AnswerPostException;
import com.coachqa.exception.ApplicationErrorCode;
import com.coachqa.exception.NotAuthorisedToUpdateException;
import com.coachqa.exception.QAEntityNotFoundException;
import com.coachqa.exception.QuestionPostException;
import com.coachqa.exception.TagsRequiredForQuestionException;
import com.coachqa.repository.dao.AccountDAO;
import com.coachqa.repository.dao.QuestionDAO;
import com.coachqa.service.ClassroomService;
import com.coachqa.service.QuestionService;
import com.coachqa.service.listeners.question.EventPublisher;
import com.coachqa.ws.controllor.QueryCriteria;
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

	private static final int NO_OF_PAGINATED_RESULTS = 5;
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

    @Autowired
    private AccountDAO accountDAO;


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

		if(!isClassroomProvided(question)){
			throw new QuestionPostException( ApplicationErrorCode.QUESTION_POST_PRIVATE, "Private question can only be posted to a valid classroom");
		}

		if(isUserMemberOfClassroom(question.getClassroomId(), question.getPostedBy().getAppUserId())){
			throw new QuestionPostException( ApplicationErrorCode.QUESTION_POST_CLASSROOM);
		}

		boolean approvalRequired = isApprovalRequired(question.getPostedBy());
		Question qstn = transactionTemplate.execute(new TransactionCallback<Question>() {
            @Override
            public Question doInTransaction(TransactionStatus transactionStatus) {
				question.setApprovalStatus(true);
            	if(approvalRequired){
            		question.setApprovalStatus(false);
				}

				// TODO: 06/01/18 the default approval status in the post table is 1 (false). This also needs to be set to true in case approval is not required.
				return  questionDao.addQuestionWithTags(question);
            }
        });

		publishPostQuestionEvent(qstn, approvalRequired);

		return qstn;
	}

	@Transactional
	@Override
	public void updateQuestion(Question updatedQuestion, AppUser user) {

		Question existingQuestion = questionDao.getQuestionById(updatedQuestion.getQuestionId());
		if(existingQuestion == null){
			throw new QAEntityNotFoundException(ApplicationErrorCode.ENTITY_NOT_FOUND, "Question does not exists - "+ updatedQuestion.getQuestionId());
		}


		if(alreadyAnswered(existingQuestion)){
			throw new NotAuthorisedToUpdateException(ApplicationErrorCode.NOT_AUTHORIZEDTO_UPDATE, "Question cannotbe edited after it is answered");
		}

		validateTags(updatedQuestion.getTags());


		if(!isClassroomProvided(updatedQuestion)){
			throw new QuestionPostException( ApplicationErrorCode.QUESTION_POST_PRIVATE, "Private question can only be posted to a valid classroom");
		}

		if(isUserMemberOfClassroom(updatedQuestion.getClassroomId(), updatedQuestion.getPostedBy().getAppUserId())){
			throw new QuestionPostException( ApplicationErrorCode.QUESTION_POST_PRIVATE);
		}
        boolean approvalRequired =isApprovalRequired(user);

		Question qstn = transactionTemplate.execute(new TransactionCallback<Question>() {
			@Override
			public Question doInTransaction(TransactionStatus transactionStatus) {
				updatedQuestion.setApprovalStatus(true);
				if(approvalRequired){
					updatedQuestion.setApprovalStatus(false);
				}
				return  questionDao.updateQuestion(updatedQuestion);
			}
		});

		publishPostQuestionEvent(qstn, approvalRequired);

	}

	private boolean alreadyAnswered(Question existingQuestion) {
		return existingQuestion.getAnswers() != null && !existingQuestion.getAnswers().isEmpty();
	}

	private void publishPostQuestionEvent(Question qstn, boolean approvalRequired) {

		Integer questionId = qstn.getQuestionId();

		/*
		Check classroom settings whether approval is required or not
		 */
		if(approvalRequired){
			// stage 1 indicates approval pending
			eventPublisher.publishEvent(new ApplicationEvent(EventType.QUESTION_POSTED, questionId, EventStage.STAGE_ONE));
		}
		else
		{
			// stage 2 indicates approval not needed
			eventPublisher.publishEvent(new ApplicationEvent(EventType.QUESTION_POSTED, questionId, EventStage.STAGE_TWO));
		}


	}

	/*
	    If a question is public then it cannot be in a non-public classroom
	    Public / non-public should be a property of classroom not the post

	    if a user comes and just wants to post a question then it can go to a default classroom which is public
	    this classroom will belong to a public organisation
	 */
	private boolean isApprovalRequired(AppUser postedBy) {
        Account account = postedBy.getAccount();
        account = accountDAO.fetchCompleteAccountDetails(account.getAccountId());

        return account.requiresPostApproval();

	}

	private boolean isUserMemberOfClassroom(Integer classroomId , Integer postedByUserId ) {
		return !classroomService.isActiveMemberOf(classroomId, postedByUserId);
	}

	private boolean isClassroomProvided(Question question) {
		return question.getClassroomId() != null;
	}

	private void validateTags(List<Tag> tags) {
		if(tags ==  null || tags.isEmpty()){
			// should we relax this rule ?
			throw new TagsRequiredForQuestionException(ApplicationErrorCode.TAGS_REQUIRED_FOR_QUESTION);
		}
	}


	@Override
	public Question postAnswer(Integer userId, Answer answer) {

		if( answer.getQuestionId() == 0 ) {
			throw new AnswerPostException(ApplicationErrorCode.ANSWER_WITHOUT_QUESTION);
		}
		Question question = questionDao.getQuestionById(answer.getQuestionId());

		if(isUserMemberOfClassroom(question.getClassroomId(), userId)){
			throw new AnswerPostException(ApplicationErrorCode.ANSWER_PRIVATE_QUESTION);
		}

		transactionTemplate.execute(new TransactionCallbackWithoutResult() {
			@Override
			protected void doInTransactionWithoutResult(TransactionStatus transactionStatus) {
				questionDao.addAnswertoQuestion(answer);
			}
		});
		eventPublisher.publishEvent(new ApplicationEvent(EventType.QUESTION_ANSWERED, answer.getQuestionId(),
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
	public Question getQuestionByIdAndIncrementViewCount(Integer questionId, AppUser user) {

		Question question = questionDao.getQuestionById(questionId);

		question.setFavorite(questionDao.isFavorite(questionId, user.getAppUserId()));
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
	public List<Question> getUsersPosts(AppUser user, Integer page) {
		return questionDao.getUsersPosts(user.getAppUserId(), page);
	}

	@Override
	public void markAsFavorite(Integer appUserId, Integer questionId, boolean isFavorite) {
		questionDao.markAsFavorite(appUserId, questionId, isFavorite);
	}

	@Override
	public List<Question> getMyFavorites(Integer appUserId, Integer page) {
		return questionDao.getMyFavorites(appUserId, page);
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
	 * @param page
     * @param user
	 * @return
     */
	@Override
	public List<Question> findSimilarQuestions(Question criteria, int page, AppUser user) {

		Integer classroomId = criteria.getClassroomId();
		return questionDao.findSimilarQuestions(criteria, page, user, NO_OF_PAGINATED_RESULTS);
	}

	@Override
	public List<Question> findByQuery(QueryCriteria searchQuery, Integer page, AppUser loggedInUser) {



		return questionDao.findByQuery(searchQuery, page, loggedInUser , NO_OF_PAGINATED_RESULTS);
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
