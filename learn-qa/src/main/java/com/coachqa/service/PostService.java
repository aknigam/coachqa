package com.coachqa.service;

import com.coachqa.entity.Post;
import com.coachqa.enums.PostTypeEnum;
import com.coachqa.enums.QuestionRatingEnum;
import com.coachqa.ws.model.PostApproval;

public interface PostService {

	void ratePost(Integer userId, Integer postId, QuestionRatingEnum meduim);

	void vote(Integer userId, Integer postId, boolean isUpVoted, PostTypeEnum postType);

	void updateApprovalStatus(PostApproval postApproval);


	Post getPostById(Integer postId);
}
