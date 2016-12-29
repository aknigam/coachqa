package com.coachqa.repository.dao.impl;

import com.coachqa.entity.AppUser;
import com.coachqa.entity.Classroom;
import com.coachqa.entity.Question;
import com.coachqa.enums.QuestionLevelEnum;
import com.coachqa.repository.dao.QuestionDAO;
import com.coachqa.repository.dao.mapper.QuestionMapper;
import com.coachqa.repository.dao.sp.AnswerAddSproc;
import com.coachqa.repository.dao.sp.QuestionAddSproc;
import com.coachqa.repository.dao.sp.QuestionGetSproc;
import com.coachqa.util.CollectionUtils;
import com.coachqa.ws.model.AnswerModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.*;

@Component
public class QuestionDAOImpl extends BaseDao implements QuestionDAO, InitializingBean {

	private static Logger LOGGER = LoggerFactory.getLogger(QuestionDAOImpl.class);
	
	private QuestionAddSproc questionAddSproc;
	
	private QuestionGetSproc questionGetSproc;
	
	private QuestionGetSproc questionUpdateStatsSproc;
	
	private AnswerAddSproc answerAddSproc;
	
	@Override
	public void afterPropertiesSet() throws Exception {
		DataSource dataSource = getDataSource();
		questionAddSproc = new QuestionAddSproc(dataSource);
		questionGetSproc = new QuestionGetSproc(dataSource);
		answerAddSproc = new AnswerAddSproc(dataSource);
	}

    @CachePut(value="questions", key="#result.questionId")
	@Override
	public Question addQuestionWithTags(Question question) {

		Question addedQuestion = questionAddSproc.addQuestion(question);
		int questionId =  addedQuestion.getQuestionId();
		// should be able to add question even if duplicate tags are provided

		for (Integer tagId : question.getTags())
			tagQuestion(questionId, tagId);

		return addedQuestion;
	}
	private static String tagQuestionInsertQuery = "Insert into questiontag  (questionId, tagId) values (?,?)";

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
		return questionGetSproc.getQuestionById(questionId);
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


	private String incrementQuestionViewsQuery =  "Update question set NoOfViews = NoOfViews + 1 where questionId =  ?";

	private String incrementQuestionVotesQuery =  "Update Question set Votes = Votes + ? where questionId = ?";

	@Override
	public void incrementQuestionViews(Integer questionId) {

		jdbcTemplate.update(incrementQuestionViewsQuery , new Integer[]{questionId});

	}

	private String questionGetByTagQuery = "select questionId from questiontag qt" +
			" join question q on q.questionId = qt.questionId " +
			" join post p on p.postId = q.questionId " +
			" order by p.postDate desc";
	@Override
	public List<Question> getQuestionsByTag(int tagId) {
		return null;
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
	public List<Question> findSimilarQuestions(Question q) {


		QuestionQueryBuilder queryBuilder = new QuestionQueryBuilder("question", "q");
		String query = queryBuilder
				.withSelectCols("q", Arrays.asList(new String[]{"questionId","RefSubjectId","QuestionLevelId","RefQuestionStatusId","Title","LastActiveDate","IsPublic"}))
				.withSelectCols("p", Arrays.asList(new String[]{"Votes","PostedBy","Content","PostDate", "NoOfViews", "postType"}))
				.withSelectCols("u", Arrays.asList(new String[]{"Firstname","middleName","lastName"}))
				.withJoin("AppUser", "u", "appuserId", "p", "postedby", 2)
				.withJoin("post", "p", "postId", "q", "questionId", 1)
				.withJoin("questionTag", "qt", "questionId", "q", "questionId", 3)
				.withSubject(q.getRefSubjectId())
				.withClassroom(q.getClassroom())
				.withTag(q.getTags())
				.withPostedByUser(q.getPostedBy())
				.withPublicOnly(q.isPublicQuestion())
				.buildQuery();

		RowMapper<Question> qm= new QuestionMapper();
		List<Question> qstns = jdbcTemplate.query(query, qm);

		return qstns;


	}

	public static void main(String[] args) {
		QuestionQueryBuilder queryBuilder = new QuestionQueryBuilder("question", "q");
		String query = queryBuilder
				.withSelectCols("q", Arrays.asList(new String[]{"questionId","RefSubjectId","QuestionLevelId","RefQuestionStatusId","Title","LastActiveDate","IsPublic"}))
				.withSelectCols("p", Arrays.asList(new String[]{"Votes","PostedBy","Content","PostDate"}))
				.withSelectCols("u", Arrays.asList(new String[]{"Firstname","middleName","lastName"}))
				.withJoin("AppUser", "u", "appuserId", "p", "postedby", 2)
				.withJoin("post", "p", "postId", "q", "questionId", 1)
				.withJoin("questionTag", "qt", "questionId", "q", "questionId", 3)
				.withSubject(1)
				.withClassroom(new Classroom(1, ""))
				.withTag(Arrays.asList(new Integer[]{1, 2}))
				.withPostedByUser(new AppUser(1, "", "", "", ""))
				.withPublicOnly(true)
				.buildQuery();

		System.out.println(query);


	}

	static class QueryCondition<T>{


		private static String AND = "and";
		private static String OR = "or";

		private final String lhs;
		private final T rhs;
		private Collection<T> vals;
		private JoinTypeEnum joinType = JoinTypeEnum.EQUALS;

		public QueryCondition(String lhs , T rhs){
			this.lhs =  lhs;
			this.rhs = rhs;
		}

		public String getOrCondition(){
			return " "+ OR + " " + lhs +" = " + rhs;
		}

		public String getAndCondition(){
			switch (joinType){
				case EQUALS:
					return " "+ lhs +" = " + rhs;
				case IN:
					if(CollectionUtils.isEmpty(vals)){
						StringBuilder q = new StringBuilder( " "+ AND + " " + lhs +" in (");
						int i = 0;
						for(T val : vals){
							if(i == vals.size() -1)
								q.append(val.toString()).append(")");
							else
								q.append(val.toString()).append(",");
						}
						return q.toString();
					}
				default:
					return "";
			}
		}

		public QueryCondition withJoinType(JoinTypeEnum joinType) {
			this.joinType =  joinType;
			return this;
		}
	}

	static class QueryConditionBuilder{

	}

	static enum JoinTypeEnum{
		EQUALS,
		IN
	}

	static class JoinTable implements Comparable<JoinTable>{

		private final String alias;
		private final int joinOrder;
		private String tableName ;
		private QueryCondition joinCondition;

		public JoinTable(String table, String alias, int order){
			tableName = table;
			this.alias = alias;
			this.joinOrder = order;
		}

		public JoinTable onJoinCondition(QueryCondition joinCondition) {
			this.joinCondition = joinCondition;
			return this;
		}

		@Override
		public String toString() {
			return alias+"."+tableName+"-"+ joinOrder;
		}

		@Override
		public int compareTo(JoinTable o) {
			if(joinOrder > o.joinOrder){
				return 1;
			}
			if(joinOrder < o.joinOrder){
				return -1;
			}
			else{
				return 0;
			}

		}
	}

	static class QuestionQueryBuilder{

		private static final String SPACE = " ";
		private static final String DOT = ".";
		private static Logger LOGGER = LoggerFactory.getLogger(QuestionQueryBuilder.class);

		private Boolean mostViewed = null;
		private final String alias;
		private String tableName ;

		private List<String> selectCols = new ArrayList<>();
		private List<QueryCondition> conditions = new ArrayList<>();
		private List<JoinTable> joins = new ArrayList<>();

		public QuestionQueryBuilder(String table, String alias){
			tableName = table;
			this.alias = alias;
		}

		public String buildQuery() {

			StringBuilder query = new StringBuilder();

			query.append("SELECT").append("\n");
			int j = 0;
			for (String col : selectCols){
				if(j++ == selectCols.size() -1)
					query.append(col).append("\n");
				else
					query.append(col).append(",").append("\n");
			}


			this.joins= new ArrayList<JoinTable>(new HashSet<>(this.joins));
			Collections.sort(this.joins);
			query.append("From").append(SPACE).append(tableName).append(" ").append(alias).append(" ").append("\n");
			for(JoinTable jt : this.joins){
				query.append("JOIN").append(SPACE).append(jt.tableName).append(SPACE).append(jt.alias)
						.append(SPACE).append("ON").append(SPACE).append(jt.joinCondition.getAndCondition())
						.append("\n");
			}
			if(CollectionUtils.isEmpty(conditions)){
				return query.toString();
			}
			query.append("WHERE ");
			int i = 0;
			for(QueryCondition qc: conditions){
				if(i++  == 0)
					query.append(" ").append(qc.getAndCondition()).append("\n");
				else
					query.append(" AND ").append(qc.getAndCondition()).append("\n");
			}
			return query.toString();
		}


		public QuestionQueryBuilder withSubject(Integer refSubjectId) {
			if(refSubjectId != null && refSubjectId != 0)
				conditions.add(new QueryCondition<Integer>("RefSubjectId", refSubjectId));
			return this;
		}

		public QuestionQueryBuilder withClassroom(Classroom classroom) {
			if(classroom!= null && classroom.getClassroomId()!= null) {
				conditions.add(new QueryCondition<Integer>("ClassroomId", classroom.getClassroomId()));
			}else{
				LOGGER.debug("Classroom condition not added");
			}
			return this;
		}

		public QuestionQueryBuilder withTag(List<Integer> tagId) {
			if(CollectionUtils.isEmpty(tagId)){
				return this;
			}
			conditions.add(new QueryCondition<Integer>("TagId", tagId.get(0)).withJoinType(JoinTypeEnum.EQUALS));
			return this;
		}


		public QuestionQueryBuilder withDateRange() {
			return this;
		}

		public QuestionQueryBuilder withMostViewed() {
			mostViewed  = true;
			return this;
		}

		public QuestionQueryBuilder withDifficultyLevel(QuestionLevelEnum questionLevelEnum) {
			return this;
		}

		public QuestionQueryBuilder withAnsweredQuestionsOnly() {
			return this;
		}

		public QuestionQueryBuilder withMostActiveQuestions() {
			return this;
		}

		public QuestionQueryBuilder withPostedByUser(AppUser postedBy) {
			if(postedBy == null)
				return this;


			conditions.add(new QueryCondition<Integer>("PostedBy", postedBy.getAppUserId()).withJoinType(JoinTypeEnum.EQUALS));
			return this;
		}

		public QuestionQueryBuilder withPublicOnly(Boolean aPublic) {
			if(aPublic == null)
				return this;
			conditions.add(new QueryCondition<Integer>("IsPublic", aPublic? 1: 0).withJoinType(JoinTypeEnum.EQUALS));
			return this;
		}


		public QuestionQueryBuilder withSelectCols(String alias, List<String> selects) {
			if(CollectionUtils.isEmpty(selects)){
				return this;
			}
			for (String col : selects){
				selectCols.add(alias+"."+col);
			}
			return this;
		}

		public QuestionQueryBuilder withJoin(String joinTableName, String joinTableALias, String col, String targetTableAlias, String targetCol, int joinOrder) {
			joins.add(new JoinTable(joinTableName, joinTableALias, joinOrder)
					.onJoinCondition(new QueryCondition<String>(targetTableAlias+"."+targetCol, joinTableALias+"."+col)
							.withJoinType(JoinTypeEnum.EQUALS)));
			return this;
		}
	}


}
