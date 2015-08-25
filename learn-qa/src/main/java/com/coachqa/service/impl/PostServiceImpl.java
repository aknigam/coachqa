package com.coachqa.service.impl;

import com.coachqa.entity.Question;
import com.coachqa.enums.QuestionRatingEnum;
import com.coachqa.repository.dao.QuestionDAO;
import com.coachqa.service.PostService;
import com.coachqa.service.listeners.ApplicationEventListener;
import com.coachqa.service.listeners.question.PublishQuestionToQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Component
public class PostServiceImpl implements PostService {

	@Autowired
	private QuestionDAO postDao;

	private ApplicationEventListener questionPostPublisher = new PublishQuestionToQueue();


	@Override
	public void ratePost(Integer userId, Integer postId, QuestionRatingEnum meduim) {

	}

	@Override
	@Transactional
	public void vote(Integer userId, Integer questionId, boolean upOrDown) {
		Map<Integer, Boolean> votedQuestions =  postDao.getVotedQuestions(userId);
		if(votedQuestions.get(questionId)!= null)
		{
			if(votedQuestions.containsKey(questionId) && Boolean.compare(votedQuestions.get(questionId), upOrDown) == 0)
			{
				throw new RuntimeException("You have already voted this question "+ upOrDown);
			}
		}
		postDao.vote(questionId, userId, upOrDown);

		Question question = postDao.getQuestionById(questionId);
		if(!upOrDown && question.getVotes() ==0){
			return;
		}
		int vote = upOrDown? 1:-1;
		postDao.incrementQuestionVotes(questionId, vote);
		question.setVotes(question.getVotes() + vote);
	}

}
