package com.coachqa.repository.dao;

import com.coachqa.entity.Question;
import com.coachqa.ws.model.AnswerModel;

import java.util.List;

public interface QuestionDAO {

	Question addQuestionWithTags(Question question);

	Question getQuestionById(Integer questionId);

	void addAnswertoQuestion(AnswerModel answer);

	void updateStats(Question question);

	void incrementQuestionViews(Integer questionId);

	List<Question> getQuestionsByTag(int tagId);

	List<Question> findSimilarQuestions(Question criteria);

	Question updateQuestion(Question updatedQuestion);
}
