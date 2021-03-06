package com.coachqa.repository.dao.impl;

import com.coachqa.entity.Answer;
import com.coachqa.entity.AppUser;
import com.coachqa.entity.Question;
import com.coachqa.repository.dao.QuestionDAO;
import com.coachqa.repository.dao.mapper.AnswerSprocMapper;
import com.coachqa.repository.dao.mapper.QuestionMapper;
import com.coachqa.repository.dao.mybatis.mapper.AnswerMapper;
import com.coachqa.repository.dao.mybatis.mapper.PostMapper;
import com.coachqa.repository.dao.mybatis.mapper.QuestionMybatisMapper;
import com.coachqa.repository.dao.mybatis.mapper.TagMapper;
import com.coachqa.repository.dao.sp.AnswerAddSproc;
import com.coachqa.util.CollectionUtils;
import com.coachqa.ws.controllor.QueryCriteria;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Component
public class QuestionDAOImpl extends BaseDao implements QuestionDAO, InitializingBean {


	private static Logger LOGGER = LoggerFactory.getLogger(QuestionDAOImpl.class);
	

	private static String incrementQuestionViewsQuery =  "Update post set noofviews = noofviews + 1 where postId =  ?";

	private static String incrementQuestionVotesQuery =  "Update post set votes = votes + ? where postId = ?";

	private static String tagQuestionInsertQuery = "Insert into questiontag  (questionid, tagId) values (?,?)";

	private AnswerAddSproc answerAddSproc;


	@Autowired
	public PostMapper postMapper;


	@Autowired
	private QuestionMybatisMapper questionMapper;

	@Autowired
	private TagMapper tagMapper;

	@Autowired
	private AnswerMapper answerMapper;


	@Override
	public void afterPropertiesSet() throws Exception {
		DataSource dataSource = getDataSource();

		answerAddSproc = new AnswerAddSproc(dataSource);
	}

//    @CachePut(value="questions", key="#result.postId")
	@Override
	public Question addQuestionWithTags(Question question) {

		postMapper.addPost(question);
		questionMapper.addQuestion(question);

		tagMapper.addTags(question.getPostId(), question.getTagIds());

		question.setQuestionId(question.getPostId());

		return question;
	}


	private void tagQuestion(int questionId, Integer tagId) {
		try {
			jdbcTemplate.update(tagQuestionInsertQuery, new Integer[]{questionId, tagId});
		} catch(DuplicateKeyException dke){
			LOGGER.warn("question could not be tagged as the tag is already associated with the question. ");
		}
	}

	@Cacheable(value="questions", key="#questionId")
	@Override
	public Question getQuestionById(Integer questionId) {
		try{

			List<Question> questions = questionMapper.getUsersQuestions(null, questionId, 3, 0);
			if(org.springframework.util.CollectionUtils.isEmpty(questions)) {
				throw new RuntimeException("Question not found");
			}
//			getQuestionById(questionId);
			return questions.get(0);
		}
		catch (DataAccessException se){
			LOGGER.error("question does not exists: "+ questionId, se);
			return null;
		}
	}

	@Override
	@CacheEvict(value="questions", key="#answer.questionId")
	public void addAnswertoQuestion(Answer answer) {
		postMapper.addPost(answer);
		answerMapper.addAnswer(answer.getPostId(), answer.getQuestionId());
	}

	@Override
	public void updateStats(Question question) {
		// TODO Auto-generated method stub
		
	}




	@Override
	public void incrementQuestionViews(Integer questionId) {

		jdbcTemplate.update(incrementQuestionViewsQuery , new Integer[]{questionId});

	}

	/*
	This method may not be required. Search endpoint should be able to handle most
	use cases.
	 */
	@Deprecated
	@Override
	public List<Question> getQuestionsByTag(int tagId) {
		// TODO: 24/01/18 pagination support?
		return tagMapper.getQuestionsByTag(tagId);
	}

	@Override
	public List<Question> findByQuery(QueryCriteria q, Integer page, AppUser loggedInUser, int
			noOfPaginatedResults) {

		QuestionQueryBuilder queryBuilder = new QuestionQueryBuilder("question", "q");

		queryBuilder
				= queryBuilder
				.withSelectCols("q", Arrays.asList(new String[]{"questionid","refsubjectid","questionlevelid","refquestionstatusid","title","lastactivedate"}))
				.withSelectCols("p", Arrays.asList(new String[]{"votes","postedby","content","postdate", "noofviews", "posttype", "classroomid", "approvalstatus"}))
				.withSelectCols("u", Arrays.asList(new String[]{"firstname","middlename","lastName"}))
				.withJoin("appuser", "u", "appuserId", "p", "postedby", 2)
				.withJoin("post", "p", "postId", "q", "questionid", 1)
				.withJoin("refsubject", "s", "refsubjectid", "q", "refsubjectid" , 3);


		if(!CollectionUtils.isEmpty(q.getSubject())) {
			queryBuilder.withCondition(new QuestionQueryBuilder.QueryCondition<String>("s.subjectname", q.getSubject())
					.withJoinType(QuestionQueryBuilder.JoinTypeEnum.IN));
		}

		if(!CollectionUtils.isEmpty(q.getTag())) {
			queryBuilder.withTagNameCondition(q.getTag());
		}


		if(!CollectionUtils.isEmpty(q.getPostedBy())) {
			queryBuilder.withCondition(new QuestionQueryBuilder.QueryCondition<String>("u.firstname", q.getPostedBy()).
					withJoinType(QuestionQueryBuilder.JoinTypeEnum.IN));
		}

		if(!q.getSimpleParam().isEmpty()) {
			queryBuilder.withCondition(new QuestionQueryBuilder.QueryCondition<String>("title", q.getSimpleParam())
					.withJoinType(QuestionQueryBuilder.JoinTypeEnum.LIKE));
		}



		queryBuilder
				.withAccountId(loggedInUser.getAccount().getAccountId())
				.withApprovedOnly()
				.withOderBy("p", "postdate", QuestionQueryBuilder.ORDER.DESC)
                .withLimit(page, noOfPaginatedResults);

		String query = queryBuilder.buildQuery();

		LOGGER.info(query);

		RowMapper<Question> qm= new QuestionMapper();
		List<Question> qstns = jdbcTemplate.query(query, qm);

		return qstns;
	}

	@Override
	public List<Question> getQuestions(List<Integer> questionIds) {
		return questionMapper.getQuestions(questionIds);
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
	 *
	 * joins.add(new JoinTable("post", "p", 1)
	 .onJoinCondition(new QueryCondition<String>(alias+".QuestionId", "p.PostId")
	 .withJoinType(JoinTypeEnum.EQUALS)));

	 joins.add(new JoinTable("questionTag", "qt", 3)
	 .onJoinCondition(new QueryCondition<String>(alias+".questionId", "qt.questionId")
	 .withJoinType(JoinTypeEnum.EQUALS)));


	 *
	 * ][[][
	 * @return
	 */
	@Override
	public List<Question> findSimilarQuestions(Question q, int page, AppUser user, int noOfResults) {

		// todo: access check should also be added here. i.e the logged in user should be the member of the classroom
		// todo: if the question is private.

		/*
		user either searches all public questions or questions of a classroom
		Case 1: classroom provided
			If user is a the member of the classroom then privacy check is not required
			otherwise only public questions from this classroom should be returned
		Case 2:classroom not provided
			return public questions only.
		*/


		QuestionQueryBuilder queryBuilder = new QuestionQueryBuilder("question", "q");

		queryBuilder
		 = queryBuilder
				.withSelectCols("q", Arrays.asList(new String[]{"questionid","refsubjectid","questionlevelid","refquestionstatusid","title","lastactivedate"}))
				.withSelectCols("p", Arrays.asList(new String[]{"votes","postedby","content","postdate", "noofviews", "posttype", "classroomid", "approvalstatus"}))
				.withSelectCols("u", Arrays.asList(new String[]{"firstname","middlename","lastName"}))
				.withSelectCols("c", Arrays.asList(new String[]{"ClassName"}))
				.withSelectCols("s", Arrays.asList(new String[]{"subjectname"}))
				.withJoin("appuser", "u", "appuserId", "p", "postedby", 2)
				.withJoin("post", "p", "postId", "q", "questionid", 1)
				.withJoin("classroom", "c", "classroomid", "p", "classroomid", 3)
				.withJoin("refsubject", "s", "refsubjectid", "q", "refsubjectid", 4)
				.withSubject(q.getRefSubjectId())
				.withClassroom(q.getClassroom())
				.withPostedByUser(q.getPostedBy())
				// TODO: 10/02/19 this is a bug
//				.withPublicOnly(true)
				.withAccountId(user.getAccount().getAccountId())
				.withApprovedOnly()
				.withOderBy("p", "postdate", QuestionQueryBuilder.ORDER.DESC)
				.withLimit(page, noOfResults);


		if(tagCriteriaExists(q)) {
			queryBuilder.withTagsCondition(q.getTagIds());
		}
		
		String query = queryBuilder.buildQuery();
		RowMapper<Question> qm= new QuestionMapper();
		List<Question> qstns = jdbcTemplate.query(query, qm);

		return qstns;


	}

	public static void main(String[] args) {
		System.out.println(UUID.randomUUID());
	}

	private boolean tagCriteriaExists(Question q) {
		return q.getTags()!= null && !q.getTags().isEmpty();
	}


	@CacheEvict(value="questions", key="#result.postId")
	@Override
	public Question updateQuestion(Question updatedQuestion) {
		// classroom, content, updatedate, approval status
		postMapper.updateQuestion(updatedQuestion);
		// subject, title, isPublic
		questionMapper.updateQuestion(updatedQuestion);

		tagMapper.deleteTags(updatedQuestion.getQuestionId());
		tagMapper.addTags(updatedQuestion.getQuestionId(), updatedQuestion.getTagIds());

		return updatedQuestion;
	}

	/*
		not fetching questions and answers data in the same query because questions and answers content can be large

	 */
	@Override
	public List<Question> getUsersPosts(Integer appUserId, Integer page) {
		List<Question> questions =  questionMapper.getUsersQuestions(appUserId, null, 2,page*PAGE_SIZE);

		return questions;
	}

	@Override
	public void markAsFavorite(Integer appUserId, Integer questionId, boolean isFavorite) {
		try {
			if (isFavorite) {
				questionMapper.markAsFavorite(appUserId, questionId);

			} else {
				questionMapper.removeFromFavorites(appUserId, questionId);
			}
		}catch (Exception e){
			if(isFavorite) {
				LOGGER.warn("question already marked favorite", e);
			}
			else {
				LOGGER.warn("question not a favorite", e);
			}
		}
	}

	@Override
	public List<Question> getMyFavorites(Integer appUserId, Integer page) {
		return questionMapper.getUsersQuestions(appUserId, null, 1,page*PAGE_SIZE);
	}

	@Override
	public boolean isFavorite(Integer questionId, Integer appUserId) {
		return  questionMapper.isFavorite(questionId, appUserId);
	}


}
