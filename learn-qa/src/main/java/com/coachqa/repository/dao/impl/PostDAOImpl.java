package com.coachqa.repository.dao.impl;

import com.coachqa.entity.AppUser;
import com.coachqa.entity.Post;
import com.coachqa.entity.QuestionVote;
import com.coachqa.enums.PostTypeEnum;
import com.coachqa.repository.dao.PostDAO;
import com.coachqa.ws.model.PostApproval;
import org.joda.time.DateTime;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class PostDAOImpl extends BaseDao implements PostDAO {


	private String votesInsertQuery = "insert into PostVote (VotedByUserId, postId  ,UpOrDown, VoteDate, postType) " +
			"values (?,?,?,?,?)";

	private String incrementPostViewsQuery =  "Update question set NoOfViews = NoOfViews + 1 where questionId =  ?";

	private String incrementPostVotesQuery =  "Update Post set Votes = Votes + ? where postId = ?";



	@Override
	public Map<Integer, Boolean> getVotedPosts(Integer userId) {
		return getUserVotedQuestions(userId);
	}
	private Map<Integer, Map<Integer, Boolean>> allUserVotedPosts = new HashMap<>();
	private Map<Integer, Map<Integer, Boolean>> allUserVotedAnswers = new HashMap<>();

	@Override
	@CacheEvict(value="questions", key="#postId")
	public void vote(final Integer postId, final PostTypeEnum postType, final Integer userId, final boolean isUpVote) {

		KeyHolder holder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(votesInsertQuery, Statement.RETURN_GENERATED_KEYS);
				ps.setInt(1, userId);
				ps.setInt(2, postId);
				ps.setBoolean(3, isUpVote);

				ps.setTimestamp(4, new Timestamp(System.currentTimeMillis()));
				ps.setInt(5, postType.getType());
				return ps;
			}
		}, holder);

		Integer id = holder.getKey().intValue();
		int voteChangeAmount = isUpVote? 1:-1;
		adjustVotes(postId, voteChangeAmount);

	}



	@Override
	public void incrementQuestionViews(Integer questionId) {

		jdbcTemplate.update(incrementPostViewsQuery, new Integer[]{questionId});

	}

	@Override
	public void adjustVotes(Integer questionId, int votes) {
		jdbcTemplate.update(incrementPostVotesQuery, new Integer[]{votes, questionId });
	}

	private String postGetQuery = "select postId, postType, postedBy, postDate, Votes, classroomId from post where postid = ?";
	@Override
	public Post getPostById(final Integer postId) {


		List<Post> posts = jdbcTemplate.query(postGetQuery, new Integer[]{postId}, new RowMapper<Post>() {
			@Override
			public Post mapRow(ResultSet rs, int i) throws SQLException {
				Post p = new Post();
				AppUser au = new AppUser();
				au.setAppUserId(rs.getInt("postedBy"));
				p.setPostedBy(au);
				p.setPostId(postId);
				p.setVotes(rs.getInt("Votes"));


				return p;
			}
		});

		if(posts!= null && posts.size()==0){
			throw new RuntimeException("Post not found: "+ postId);
		}

		return posts.get(0);



	}

	private String updatePostApprovalQuery =  "Update Post set ApprovalStatus =  ? , " +
			" ApprovalComment = ? " +
			" where postId = ? ";

	@Override
	public void updatePostApproval(PostApproval postApproval) {
		jdbcTemplate.update(updatePostApprovalQuery, new Object[]{postApproval.isApproved() ? 0 : 1, postApproval.getComments(), postApproval.getPostId() });
	}

	private String votesQuery = "select VotedByUserId, PostId  ,UpOrDown, VoteDate " +
			"from PostVote where VotedByUserId = ? order by VoteDate desc limit 1";

	private Map<Integer,Boolean> getUserVotedQuestions(Integer userId) {

		final Map<Integer, Boolean> userVotedQuestions = new HashMap<>();
		jdbcTemplate.query(votesQuery, new Integer[]{userId}, new RowMapper<QuestionVote>() {
			@Override
			public QuestionVote mapRow(ResultSet rs, int i) throws SQLException {
				int postId = rs.getInt(2);
				int votedByUserId = rs.getInt(1);
				boolean upOrDown = rs.getBoolean(3);
				Time voteDate = rs.getTime(4);
				userVotedQuestions.put(postId, upOrDown);
				return new QuestionVote(votedByUserId, upOrDown, new DateTime(voteDate.getTime()), postId);
			}
		});
		return userVotedQuestions;
	}


}
