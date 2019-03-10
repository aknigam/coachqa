package com.coachqa.service.impl;

import com.coachqa.entity.AppUser;
import com.coachqa.entity.Classroom;
import com.coachqa.entity.Post;
import com.coachqa.entity.PostApprovalRequest;
import com.coachqa.enums.PostTypeEnum;
import com.coachqa.enums.QuestionRatingEnum;
import com.coachqa.exception.ApplicationErrorCode;
import com.coachqa.exception.QAEntityNotFoundException;
import com.coachqa.repository.dao.PostDAO;
import com.coachqa.service.ClassroomService;
import com.coachqa.service.PostService;
import com.coachqa.service.listeners.question.EventPublisher;
import com.coachqa.ws.model.PostApproval;
import notification.entity.ApplicationEvent;
import notification.entity.EventStage;
import notification.entity.EventType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class PostServiceImpl implements PostService {

	@Autowired
	private PostDAO postDao;

	@Autowired
	@Lazy
	private EventPublisher postPublisher;

	@Autowired
	private ClassroomService classroomService;

	// TODO: 17/04/17 to be implemented
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
	@CacheEvict(value="questions", key="#postApproval.postId")
	public void updateApprovalStatus(PostApproval postApproval) {
		Post post = postDao.getPostById(postApproval.getPostId());

		AppUser approver = postApproval.getApprovedBy();
		if(!isApproverClassroomAdmin(approver, post.getClassroom())) {
			throw new RuntimeException("Only classroom admin can approve the post");
		}
		postDao.updatePostApproval(postApproval);

		ApplicationEvent event = new ApplicationEvent(EventType.POST_APPROVED, postApproval.getPostId(), post
				.getPostedBy().getAppUserId(),
				new Date(System.currentTimeMillis()), new Date(System.currentTimeMillis()));
		event.setStage(EventStage.STAGE_TWO);
		postPublisher.publishEvent(event);
	}

	private boolean isApproverClassroomAdmin(AppUser approver, Classroom classroom) {

		if(classroom.getClassOwner() == null) {
			classroom = classroomService.getClassroom(classroom.getClassroomId());
		}

		return classroom.getClassOwner().equals(approver);
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

	@Override
	public void deletePost(Integer postId) {
		// TODO: 14/04/18  
	}

	@Override
	public List<PostApprovalRequest> getPostsPendingApproval(Integer appUserId, Integer page) {
		// each element in the list should also have the questionId of the post.
		// postid is the questionId
		// posttype indicates whether the question itself or one of its answers is pending approval
		List<Post> posts =  postDao.getPendingApprovalPosts(appUserId, page);

		List<PostApprovalRequest> pers= new ArrayList<>();

		for (Post p : posts) {
			PostApprovalRequest request = new PostApprovalRequest();
			request.setRequestedBy(p.getPostedBy());
			request.setPost(p);
			request.setPostType(p.getPostTypeEnum());
			pers.add(request);
		}
		return pers;

	}

	@Override
	public void updatePostExtractedtext(Integer postId, String imageExtractedText) {
		postDao.updatePostExtractedtext( postId,  imageExtractedText);
	}

}
