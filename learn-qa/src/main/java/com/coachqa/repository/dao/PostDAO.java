package com.coachqa.repository.dao;

import com.coachqa.entity.Post;
import com.coachqa.enums.PostTypeEnum;

import java.util.Map;

public interface PostDAO {


	Map<Integer,Boolean> getVotedPosts(Integer userId);

	void vote(Integer questionId, PostTypeEnum postType, Integer userId, boolean upOrDown);

	void incrementQuestionViews(Integer questionId);

	void adjustVotes(Integer questionId, int votes);

	Post getPostById(Integer postId);
}
