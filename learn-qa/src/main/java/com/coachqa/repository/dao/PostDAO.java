package com.coachqa.repository.dao;

import com.coachqa.entity.Post;
import com.coachqa.entity.Question;
import com.coachqa.enums.PostTypeEnum;
import com.coachqa.ws.model.AnswerModel;
import com.coachqa.ws.model.QuestionModel;

import java.util.Map;

public interface PostDAO {


	Map<Integer,Boolean> getVotedPosts(Integer userId);

	void vote(Integer questionId, PostTypeEnum postType, Integer userId, boolean upOrDown);

	void incrementQuestionViews(Integer questionId);

	void adjustVotes(Integer questionId, int votes);

	Post getPostById(Integer postId);
}
