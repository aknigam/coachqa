package com.coachqa.repository.dao.impl;

import javax.sql.DataSource;

import com.coachqa.entity.QuestionVote;
import org.joda.time.DateTime;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import com.coachqa.entity.Question;
import com.coachqa.repository.dao.QuestionDAO;
import com.coachqa.repository.dao.sp.AnswerAddSproc;
import com.coachqa.repository.dao.sp.QuestionAddSproc;
import com.coachqa.repository.dao.sp.QuestionGetSproc;
import com.coachqa.ws.model.AnswerModel;
import com.coachqa.ws.model.QuestionModel;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

@Component
public class QuestionDAOImpl extends BaseDao implements QuestionDAO, InitializingBean {

	
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
	public Question addQuestion(QuestionModel question) {

		return questionAddSproc.addQuestion(question);


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

	private String votesInsertQuery = "insert into QuestionVote (VotedByUserId, Questionid  ,UpOrDown, VoteDate) " +
			"values (?,?,?,?)";



	@Override
	public Map<Integer, Boolean> getVotedQuestions(Integer userId) {
		return getUserVotedQuestions(userId);
	}
	private Map<Integer, Map<Integer, Boolean>> allUserVotedQuestions = new HashMap<>();
	private Map<Integer, Map<Integer, Boolean>> allUserVotedAnswers = new HashMap<>();

	@Override
	public void vote(final Integer questionId, final Integer userId, final boolean upOrDown) {

		KeyHolder holder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(votesInsertQuery, Statement.RETURN_GENERATED_KEYS);
				ps.setInt(1, userId);
				ps.setInt(2, questionId);
				ps.setBoolean(3, upOrDown);

				ps.setTimestamp(4, new Timestamp(System.currentTimeMillis()));
				return ps;
			}
		}, holder);

		Integer id = holder.getKey().intValue();




	}

	@Override
	public Map<Integer, Boolean> getVotedAnswers(Integer userId) {
		return getUserVotedAnswers(userId);
	}


	private String incrementQuestionViewsQuery =  "Update question set NoOfViews = NoOfViews + 1 where questionId =  ?";

	private String incrementQuestionVotesQuery =  "Update Question set Votes = Votes + ? where questionId = ?";

	@Override
	public void incrementQuestionViews(Integer questionId) {

		jdbcTemplate.update(incrementQuestionViewsQuery , new Integer[]{questionId});

	}

	@Override
	public void incrementQuestionVotes(Integer questionId, int votes) {
		jdbcTemplate.update(incrementQuestionVotesQuery , new Integer[]{votes, questionId });
	}

	private Map<Integer,Boolean> getUserVotedAnswers(Integer userId) {
		Map<Integer, Boolean> userVotedAnswers = allUserVotedAnswers.get(userId);
		if(userVotedAnswers == null) {
			userVotedAnswers = new HashMap<>();
			allUserVotedAnswers.put(userId, userVotedAnswers);
		}
		return userVotedAnswers;
	}

	private String votesQuery = "select VotedByUserId, Questionid  ,UpOrDown, VoteDate from QuestionVote where VotedByUserId = ? order by VoteDate desc limit 1";

	private Map<Integer,Boolean> getUserVotedQuestions(Integer userId) {

		final Map<Integer, Boolean> userVotedQuestions = new HashMap<>();
		jdbcTemplate.query(votesQuery, new Integer[]{userId}, new RowMapper<QuestionVote>() {
			@Override
			public QuestionVote mapRow(ResultSet rs, int i) throws SQLException {
				int questionId = rs.getInt(2);
				int votedByUserId = rs.getInt(1);
				boolean upOrDown = rs.getBoolean(3);
				Time voteDate = rs.getTime(4);
				userVotedQuestions.put(questionId, upOrDown);
				return new QuestionVote(votedByUserId, upOrDown, new DateTime(voteDate.getTime()), questionId);
			}
		});
		return userVotedQuestions;
	}


}
