package com.coachqa.repository.dao;

import com.coachqa.entity.Question;
import com.coachqa.ws.model.AnswerModel;
import com.coachqa.ws.model.QuestionModel;

import java.util.Map;

public interface QuestionDAO {

	Question addQuestion(QuestionModel model);

	Question getQuestionById(Integer questionId);

	void addAnswertoQuestion(AnswerModel answer);

	void updateStats(Question question);

	Map<Integer,Boolean> getVotedQuestions(Integer userId);

	void vote(Integer questionId, Integer userId, boolean upOrDown);

	Map<Integer,Boolean> getVotedAnswers(Integer userId);

	void incrementQuestionViews(Integer questionId);

	void incrementQuestionVotes(Integer questionId, int votes);
}
