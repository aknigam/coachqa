package com.coachqa.service;

import com.coachqa.entity.Post;
import com.coachqa.entity.PostApprovalRequest;
import com.coachqa.enums.PostTypeEnum;
import com.coachqa.enums.QuestionRatingEnum;
import com.coachqa.ws.model.PostApproval;

import java.util.List;

public interface PostService {

	void ratePost(Integer userId, Integer postId, QuestionRatingEnum meduim);

	void vote(Integer userId, Integer postId, boolean isUpVoted, PostTypeEnum postType);

	void updateApprovalStatus(PostApproval postApproval);

	Post getPostById(Integer postId);

    void deletePost(Integer postId);

	List<PostApprovalRequest> getPostsPendingApproval(Integer appUserId, Integer page);
}
