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

	void incrementQuestionViews(Integer questionId);

}
