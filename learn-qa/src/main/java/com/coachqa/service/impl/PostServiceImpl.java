package com.coachqa.service.impl;

import com.coachqa.entity.Post;
import com.coachqa.enums.PostTypeEnum;
import com.coachqa.enums.QuestionRatingEnum;
import com.coachqa.repository.dao.PostDAO;
import com.coachqa.service.PostService;
import com.coachqa.service.listeners.ApplicationEventListener;
import com.coachqa.service.listeners.SimpleRetryingEventListener;
import com.coachqa.service.listeners.question.ImageToTextQuestionConverterQuestionListener;
import com.coachqa.service.listeners.question.IndexQuestionListener;
import com.coachqa.service.listeners.question.SimpleEventPublisher;
import notification.entity.ApplicationEvent;
import notification.entity.EventStage;
import notification.entity.EventType;
import org.springframework.beans.factory.annotation.Autowired;
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
	private SimpleEventPublisher postPublisher;


	@PostConstruct
	public void init(){

		List<ApplicationEventListener<Integer>> listeners = new ArrayList<>();

		listeners.add(new SimpleRetryingEventListener( new ImageToTextQuestionConverterQuestionListener()));
		listeners.add(new SimpleRetryingEventListener( new IndexQuestionListener()));
		listeners.add(new SimpleRetryingEventListener( new UsersNotificationListener()));

		postPublisher = new SimpleEventPublisher(listeners);


	}

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
	public void updateApprovalStatus(Integer approverUserId, Integer postId, boolean isApproved, String comments, PostTypeEnum postType) {
		// update post approval status in DB
		// generate the event if post got approved
		ApplicationEvent event= new ApplicationEvent(postType == PostTypeEnum.ANSWER ? EventType.QUESTION_UPDATED : EventType.QUESTION_ANSWERED, postId);
		event.setStage(EventStage.STAGE_TWO);
		postPublisher.publishEvent(event);
	}

}
