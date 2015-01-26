package com.coachqa.service;

import com.coachqa.entity.Question;
import com.coachqa.ws.model.AnswerModel;
import com.coachqa.ws.model.QuestionModel;

public interface QuestionService {
	
	void addQuestion();
	
	void updateQuestions();
	
	void searchQuestion();
	
	void shareQuestionByEmail();

	Question addQuestion( QuestionModel model);

	Question submitAnswer(AnswerModel model);

	Question getQuestionById(Integer questionId);

	void updateStats(Question question);
	
	

}
