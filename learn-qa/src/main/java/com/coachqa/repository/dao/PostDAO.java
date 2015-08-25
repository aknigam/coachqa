package com.coachqa.repository.dao;

import com.coachqa.entity.Question;
import com.coachqa.ws.model.AnswerModel;
import com.coachqa.ws.model.QuestionModel;

import java.util.Map;

public interface PostDAO {


	Map<Integer,Boolean> getVotedQuestions(Integer userId);

	void vote(Integer questionId, Integer userId, boolean upOrDown);

	void incrementQuestionViews(Integer questionId);

	void incrementQuestionVotes(Integer questionId, int votes);
}
