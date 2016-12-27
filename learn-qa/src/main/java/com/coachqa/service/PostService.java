package com.coachqa.service;

import com.coachqa.enums.PostTypeEnum;
import com.coachqa.enums.QuestionRatingEnum;

public interface PostService {

	void ratePost(Integer userId, Integer postId, QuestionRatingEnum meduim);

	void vote(Integer userId, Integer postId, boolean isUpVoted, PostTypeEnum postType);

	void updateApprovalStatus(Integer userId, Integer postId, boolean isApproved, String comments, PostTypeEnum postType);



}
