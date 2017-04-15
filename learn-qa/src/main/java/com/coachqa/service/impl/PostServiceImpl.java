package com.coachqa.service.impl;

import com.coachqa.entity.Post;
import com.coachqa.enums.PostTypeEnum;
import com.coachqa.enums.QuestionRatingEnum;
import com.coachqa.exception.ApplicationErrorCode;
import com.coachqa.exception.QAEntityNotFoundException;
import com.coachqa.repository.dao.PostDAO;
import com.coachqa.service.PostService;
import com.coachqa.service.listeners.ApplicationEventListener;
import com.coachqa.service.listeners.SimpleRetryingEventListener;
import com.coachqa.service.listeners.question.EventPublisher;
import com.coachqa.service.listeners.question.ImageToTextQuestionConverterQuestionListener;
import com.coachqa.service.listeners.question.IndexQuestionListener;
import com.coachqa.service.listeners.question.SimpleEventPublisher;
import com.coachqa.ws.model.PostApproval;
import notification.entity.ApplicationEvent;
import notification.entity.EventStage;
import notification.entity.EventType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class PostServiceImpl implements PostService {

	@Autowired
	private PostDAO postDao;

	@Autowired
	@Lazy
	private EventPublisher postPublisher;

	@Override
	public void ratePost(Integer userId, Integer postId, QuestionRatingEnum meduim) {

	}

	@Override
	@Transactional
	public void vote(Integer userId, Integer postId, boolean isUpVote, PostTypeEnum postType) {

		Post post = postDao.getPostById(postId);
		if(!isUpVote && post.getVotes() ==0){
			return;
		}

		Map<Integer, Boolean> votedPosts =  postDao.getVotedPosts(userId);

		if(votedPosts.get(postId)!= null)
		{
			if( Boolean.compare(votedPosts.get(postId), isUpVote) == 0)
			{
				throw new RuntimeException("You have already voted this question "+ isUpVote);
			}
		}
		int voteChangeAmount = isUpVote? 1:-1;
		postDao.vote(postId,postType, userId, isUpVote);

		// this is supposed to update question cache but it is not working.
		post.setVotes(post.getVotes() + voteChangeAmount);
	}

	@Override
	public void updateApprovalStatus(PostApproval postApproval) {
		postDao.updatePostApproval(postApproval);
		ApplicationEvent event= new ApplicationEvent(postApproval.isApproved() ? EventType.ANSWER_POSTED : EventType.POST_REJECTED, postApproval.getPostId());
		postPublisher.publishEvent(event);
	}

	@Override
	public Post getPostById(Integer postId) {
		Post post = postDao.getPostById(postId);
		if(post == null){
			// TODO: 11/04/17 correct the params below
			throw new QAEntityNotFoundException(ApplicationErrorCode.ANSWER_PRIVATE_QUESTION, "message");
		}
		return post;
	}

}
