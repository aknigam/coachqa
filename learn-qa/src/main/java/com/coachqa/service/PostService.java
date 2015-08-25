package com.coachqa.service;

import com.coachqa.entity.Question;
import com.coachqa.enums.QuestionRatingEnum;
import com.coachqa.ws.model.AnswerModel;
import com.coachqa.ws.model.QuestionModel;

import java.util.List;

public interface PostService {

	void ratePost(Integer userId, Integer postId, QuestionRatingEnum meduim);

	void vote(Integer userId, Integer postId, boolean isUpVoted);


}
