package com.coachqa.repository.dao.impl;

import com.coachqa.entity.Question;
import com.coachqa.repository.dao.QuestionDAO;
import com.coachqa.repository.dao.mapper.QuestionMapper;
import com.coachqa.repository.dao.mybatis.mapper.PostMapper;
import com.coachqa.repository.dao.mybatis.mapper.QuestionMybatisMapper;
import com.coachqa.repository.dao.mybatis.mapper.TagMapper;
import com.coachqa.repository.dao.sp.AnswerAddSproc;
import com.coachqa.ws.model.AnswerModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.Arrays;
import java.util.List;

@Component
public class QuestionDAOImpl extends BaseDao implements QuestionDAO, InitializingBean {

	public static final Integer PAGE_SIZE = 5;
	private static Logger LOGGER = LoggerFactory.getLogger(QuestionDAOImpl.class);
	

	private static String incrementQuestionViewsQuery =  "Update post set NoOfViews = NoOfViews + 1 where PostId =  ?";

	private static String incrementQuestionVotesQuery =  "Update post set Votes = Votes + ? where postId = ?";

	private static String tagQuestionInsertQuery = "Insert into questiontag  (questionId, tagId) values (?,?)";

	private AnswerAddSproc answerAddSproc;


	@Autowired
	public PostMapper postMapper;


	@Autowired
	private QuestionMybatisMapper questionMapper;

	@Autowired
	private TagMapper tagMapper;

	
	@Override
	public void afterPropertiesSet() throws Exception {
		DataSource dataSource = getDataSource();

		answerAddSproc = new AnswerAddSproc(dataSource);
	}

    @CachePut(value="questions", key="#result.postId")
	@Override
	public Question addQuestionWithTags(Question question) {

		postMapper.addPost(question);
		questionMapper.addQuestion(question);

		tagMapper.addTags(question.getPostId(), question.getTags());

		question.setQuestionId(question.getPostId());

		return question;
	}


	private void tagQuestion(int questionId, Integer tagId) {
		try {
			jdbcTemplate.update(tagQuestionInsertQuery, new Integer[]{questionId, tagId});
		} catch(DuplicateKeyException dke){
			LOGGER.warn("Question could not be tagged as the tag is already associated with the question. ");
		}
	}

	@Cacheable(value="questions", key="#questionId")
	@Override
	public Question getQuestionById(Integer questionId) {
		try{

			Question question = questionMapper.getQuestionById(questionId);
			return question;
		}
		catch (DataAccessException se){
			LOGGER.error("Question does not exists: "+ questionId, se);
			return null;
		}
	}

	@Override
	@CacheEvict(value="questions", key="#answer.questionId")
	public void addAnswertoQuestion(AnswerModel answer) {
		answerAddSproc.addAnswer(answer);
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
	public List<Question> findSimilarQuestions(Question q, int page, int userId, int noOfResults) {

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
				.withSelectCols("q", Arrays.asList(new String[]{"questionId","RefSubjectId","QuestionLevelId","RefQuestionStatusId","Title","LastActiveDate","IsPublic"}))
				.withSelectCols("p", Arrays.asList(new String[]{"Votes","PostedBy","Content","PostDate", "NoOfViews", "postType", "ClassroomId", "ApprovalStatus"}))
				.withSelectCols("u", Arrays.asList(new String[]{"Firstname","middleName","lastName"}))
				.withJoin("AppUser", "u", "appuserId", "p", "postedby", 2)
				.withJoin("post", "p", "postId", "q", "questionId", 1)
				.withSubject(q.getRefSubjectId())
				.withClassroom(q.getClassroomId())
				.withPostedByUser(q.getPostedBy())
				.withPublicOnly(true)
				.withApprovedOnly()
				.withLimit(page, noOfResults);


		if(tagCriteriaExists(q)) {
			queryBuilder.withTagsCondition(q.getTags());
		}
		
		String query = queryBuilder.buildQuery();
		RowMapper<Question> qm= new QuestionMapper();
		List<Question> qstns = jdbcTemplate.query(query, qm);

		return qstns;


	}

	private boolean tagCriteriaExists(Question q) {
		return q.getTags()!= null && !q.getTags().isEmpty();
	}



	@Override
	public Question updateQuestion(Question updatedQuestion) {
		// classroom, content, updatedate, approval status
		postMapper.updateQuestion(updatedQuestion);
		// subject, title, isPublic
		questionMapper.updateQuestion(updatedQuestion);

		tagMapper.deleteTags(updatedQuestion.getQuestionId());
		tagMapper.addTags(updatedQuestion.getQuestionId(), updatedQuestion.getTags());

		return updatedQuestion;
	}

	/*
		not fetching questions and answers data in the same query because questions and answers content can be large

	 */
	@Override
	public List<Question> getUsersPosts(Integer appUserId, Integer page) {
		List<Question> questions =  questionMapper.getUsersQuestions(appUserId, page*PAGE_SIZE);

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
				LOGGER.warn("Question already marked favorite", e);
			}
			else {
				LOGGER.warn("Question not a favorite", e);
			}
		}
	}

	@Override
	public List<Question> getMyFavorites(Integer appUserId, Integer page) {
		return questionMapper.getFavoriteQuestions(appUserId, page*PAGE_SIZE);
	}

	@Override
	public boolean isFavorite(Integer questionId, Integer appUserId) {
		return  questionMapper.isFavorite(questionId, appUserId);
	}
}
