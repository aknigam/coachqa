package com.coachqa.repository.dao.impl;

import com.coachqa.entity.Post;
import com.coachqa.entity.PostVote;
import com.coachqa.entity.QuestionVote;
import com.coachqa.enums.PostTypeEnum;
import com.coachqa.repository.dao.PostDAO;
import com.coachqa.repository.dao.mybatis.mapper.PostMapper;
import com.coachqa.ws.model.PostApproval;
import org.apache.ibatis.binding.BindingException;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class PostDAOImpl extends BaseDao implements PostDAO {


	@Autowired
	private PostMapper postMapper;

	private static String updatePostApprovalQuery =  "Update post set approvalstatus =  ? , " +
			" approvalcomment = ? " +
			" where postId = ? ";

	private static String votesQuery = "select votedbyuserid, postId  ,UpOrDown, VoteDate " +
			"from postVote where votedbyuserid = ? order by VoteDate desc limit 1";




	@Override
	public Map<Integer, Boolean> getVotedPosts(Integer userId) {
		return getUserVotedQuestions(userId);
	}
	private Map<Integer, Map<Integer, Boolean>> allUserVotedPosts = new HashMap<>();
	private Map<Integer, Map<Integer, Boolean>> allUserVotedAnswers = new HashMap<>();

	@Override
	@CacheEvict(value="questions", key="#postId")
	public void vote(final Integer postId, final PostTypeEnum postType, final Integer userId, final boolean isUpVote) {


		PostVote postVote = new PostVote(postId, postType, userId, new DateTime(System.currentTimeMillis()), isUpVote);
		postMapper.postVote(postVote);
		Integer id = postVote.getPostId();
		int voteChangeAmount = isUpVote? 1:-1;
		adjustVotes(postId, voteChangeAmount);
	}



	@Override
	public void incrementQuestionViews(Integer postId) {
		postMapper.incrementPostViewsQuery(postId);
	}

	@Override
	public void adjustVotes(Integer postId, int votes) {
		postMapper.adjustVotes(postId, votes);
	}



	@Override
	public Post getPostById(Integer postId) {
		return  postMapper.getPostById(postId);
	}



	@Override
	public void updatePostApproval(PostApproval postApproval) {

		postMapper.updatePostApproval(postApproval);

	}

	@Override
	public List<Post> getPendingApprovalPosts(Integer appUserId, Integer page) {
		try {
			return postMapper.getPostsPendingApprovalByUser(appUserId, page * PAGE_SIZE);
		} catch (BindingException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void updatePostExtractedtext(Integer postId, String imageExtractedText) {

		String existingText = postMapper.extractedTextExists(postId);
		if(existingText == null || existingText.trim().equals("")) {
			postMapper.addPostExtractedtext(postId, imageExtractedText);
			return;
		}

		postMapper.updatePostExtractedtext(postId, imageExtractedText);
	}


	private Map<Integer,Boolean> getUserVotedQuestions(Integer userId) {

		final Map<Integer, Boolean> userVotedQuestions = new HashMap<>();

		List<QuestionVote> votes = postMapper.getUserVotedQuestions(userId);
		return 	votes.stream().collect(Collectors.toMap((v -> v.getPostId()), (v -> v.isUpOrDown())));

	}


}
