package com.coachqa.service.impl;

import com.coachqa.entity.Account;
import com.coachqa.entity.Answer;
import com.coachqa.entity.AppUser;
import com.coachqa.entity.Classroom;
import com.coachqa.entity.Post;
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
import com.coachqa.service.IndexSearchService;
import com.coachqa.service.QuestionService;
import com.coachqa.service.SolrIndex;
import com.coachqa.service.listeners.question.EventPublisher;
import com.coachqa.util.CollectionUtils;
import com.coachqa.ws.controllor.QueryCriteria;
import notification.entity.ApplicationEvent;
import notification.entity.EventStage;
import notification.entity.EventType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import java.sql.Date;
import java.util.Collections;
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

	@Autowired()
	@Qualifier("solrindex")
    private IndexSearchService searchService;


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

		questionPostCommonValidations(question);

		boolean approvalRequired = isApprovalRequired(question.getPostedBy(), question.getClassroom());
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

		questionEditValidations(updatedQuestion, user, existingQuestion);
		questionPostCommonValidations(updatedQuestion);

		boolean approvalRequired =isApprovalRequired(user, existingQuestion.getClassroom());
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

	private void questionEditValidations(Question updatedQuestion, AppUser user, Question existingQuestion) {
		if(existingQuestion == null){
			throw new QAEntityNotFoundException(ApplicationErrorCode.ENTITY_NOT_FOUND, "Question does not exists - "+ updatedQuestion.getQuestionId());
		}

		if(alreadyAnswered(existingQuestion)){
			throw new NotAuthorisedToUpdateException(ApplicationErrorCode.NOT_AUTHORIZEDTO_UPDATE, "Question cannotbe edited after it is answered");
		}
		if(!userAuthorisedToEdit(existingQuestion , user)){
			throw new NotAuthorisedToUpdateException(ApplicationErrorCode.NOT_AUTHORIZEDTO_UPDATE, "You cannot update" +
					" question posted by others.");
		}
	}

	private void questionPostCommonValidations(Question question) {
		validateTags(question.getTags());

		if(!isClassroomProvided(question)){
			throw new QuestionPostException( ApplicationErrorCode.QUESTION_POST_PRIVATE, "Private question can only be posted to a valid classroom");
		}

		if(isUserMemberOfClassroom(question.getClassroom().getClassroomId(), question.getPostedBy().getAppUserId())){
			throw new QuestionPostException( ApplicationErrorCode.QUESTION_POST_PRIVATE);
		}
	}

	private boolean userAuthorisedToEdit(Question existingQuestion, AppUser user) {

		if( existingQuestion.getPostedBy().getAppUserId().equals(user.getAppUserId())){
			return true;
		}
		return false;
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
	private boolean isApprovalRequired(AppUser postedBy, Classroom classroom) {

		// if the posted by is the class owner then approval not required
		AppUser owner = classroom.getClassOwner();

		if (owner == null) {
			classroom = classroomService.getClassroom(classroom.getClassroomId());
			owner = classroom.getClassOwner();
		}
		if(owner.equals(postedBy)) {
			return false;
		}

        Account account = postedBy.getAccount();
        account = accountDAO.fetchCompleteAccountDetails(account.getAccountId());

        return account.requiresPostApproval();

	}

	private boolean isUserMemberOfClassroom(Integer classroomId , Integer postedByUserId ) {
		// this is also checking implicitly that account of classroom and usr is same
		return !classroomService.isActiveMemberOf(classroomId, postedByUserId);
	}

	private boolean isClassroomProvided(Question question) {
		return question.getClassroom() != null && question.getClassroom().getClassroomId() != null;
	}

	private void validateTags(List<Tag> tags) {
		if(tags ==  null || tags.isEmpty()){
			// should we relax this rule ?
			throw new TagsRequiredForQuestionException(ApplicationErrorCode.TAGS_REQUIRED_FOR_QUESTION);
		}
	}


	@Override
	public Question postAnswer(AppUser user, Answer answer) {

		if( answer.getQuestionId() == 0 ) {
			throw new AnswerPostException(ApplicationErrorCode.ANSWER_WITHOUT_QUESTION);
		}
		Question question = questionDao.getQuestionById(answer.getQuestionId());

		if(isUserMemberOfClassroom(question.getClassroom().getClassroomId(), user.getAppUserId())){
			throw new AnswerPostException(ApplicationErrorCode.ANSWER_PRIVATE_QUESTION);
		}

		boolean approvalRequired =isApprovalRequired(user, question.getClassroom());

		transactionTemplate.execute(new TransactionCallbackWithoutResult() {
			@Override
			protected void doInTransactionWithoutResult(TransactionStatus transactionStatus) {
				answer.setApprovalStatus(true);
				if(approvalRequired){
					answer.setApprovalStatus(false);
				}
				questionDao.addAnswertoQuestion(answer);
			}
		});
		ApplicationEvent event = new ApplicationEvent(EventType.QUESTION_ANSWERED, answer.getQuestionId(),
				user.getAppUserId(),new Date(System.currentTimeMillis()),
				new Date(System.currentTimeMillis()) );
		if(approvalRequired) {
			event.setStage(EventStage.STAGE_ONE);
		}
		else {
			event.setStage(EventStage.STAGE_ONE);
		}
		eventPublisher.publishEvent(event);

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

		if(question == null) {
			throw new RuntimeException("Question with given id not found");
		}
		if(!question.getApprovalStatus() && question.getClassroom().isClassroomAdmin(user) ) {
			question.loggedInUserCanApprove(true);
		}
		if(question.getPostedBy().getAppUserId().equals(user.getAppUserId())) {
			question.setMyPost(true);
		}
		setPostedByMe(user.getAppUserId(), CollectionUtils.normaliseNullList(question.getAnswers()));

		question.setFavorite(questionDao.isFavorite(questionId, user.getAppUserId()));
		/*
		Is it right to increment the view every time the user sees it. Or should there be only one view per user?
		 */
		questionDao.incrementQuestionViews(questionId);
		question.setNoOfViews(question.getNoOfViews()+1);
		return question;
	}

	private <E extends Post> List<E> setPostedByMe(Integer user, List<E> posts) {
		for( E p : posts ) {
			if(p.getPostedBy().getAppUserId().equals(user)) {
				p.setMyPost(true);
			}
		}
		return posts;
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
		return setPostedByMe(appUserId,  questionDao.getMyFavorites(appUserId, page));
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

//		Integer classroomId = criteria.getClassroom().getClassroomId();

		/*
		1. get the questionIds from solr index
		2. Then get the content of those questions from the DB and return
		3. Above approach needs to support pagination


		 */


		if(searchService instanceof SolrIndex) {
			List<Integer> questionIds = searchService.searchQuestions(criteria, page, user);
			questionIds = questionIds == null ? Collections.emptyList() : questionIds;
			if (!questionIds.isEmpty()) {
				List<Question> questions = questionDao.getQuestions(questionIds);
				// TODO: 03/03/19 return this list
			}
		}
		return setPostedByMe(user.getAppUserId(), questionDao.findSimilarQuestions(criteria, page, user,
				NO_OF_PAGINATED_RESULTS));
	}

	@Override
	public List<Question> findByQuery(QueryCriteria searchQuery, Integer page, AppUser loggedInUser) {

		return setPostedByMe(loggedInUser.getAppUserId(), questionDao.findByQuery(searchQuery, page, loggedInUser ,
				NO_OF_PAGINATED_RESULTS));
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
