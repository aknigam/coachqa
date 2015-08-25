package com.coachqa.repository.dao.impl;

import com.coachqa.entity.Question;
import com.coachqa.entity.QuestionVote;
import com.coachqa.repository.dao.PostDAO;
import com.coachqa.repository.dao.QuestionDAO;
import com.coachqa.repository.dao.sp.AnswerAddSproc;
import com.coachqa.repository.dao.sp.QuestionAddSproc;
import com.coachqa.repository.dao.sp.QuestionGetSproc;
import com.coachqa.ws.model.AnswerModel;
import com.coachqa.ws.model.QuestionModel;
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

import javax.sql.DataSource;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;

@Component
public class PostDAOImpl extends BaseDao implements PostDAO {


	private String votesInsertQuery = "insert into QuestionVote (VotedByUserId, Questionid  ,UpOrDown, VoteDate) " +
			"values (?,?,?,?)";


	private String incrementQuestionViewsQuery =  "Update question set NoOfViews = NoOfViews + 1 where questionId =  ?";

	private String incrementQuestionVotesQuery =  "Update Question set Votes = Votes + ? where questionId = ?";



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
	public void incrementQuestionViews(Integer questionId) {

		jdbcTemplate.update(incrementQuestionViewsQuery , new Integer[]{questionId});

	}

	@Override
	public void incrementQuestionVotes(Integer questionId, int votes) {
		jdbcTemplate.update(incrementQuestionVotesQuery , new Integer[]{votes, questionId });
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
