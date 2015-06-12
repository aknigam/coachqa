package com.coachqa.service;

import com.coachqa.entity.Question;
import com.coachqa.ws.model.AnswerModel;
import com.coachqa.ws.model.QuestionModel;

public interface QuestionService {
	
	/**
	 * Post should return the list of similar questions asked earlier.
	 * user can then chose to delete his question. Its similar to directly searching.
	 * 
	 * This should store question in DB and added to index for searching.
	 * 
	 * 
	 * 
	 * Question can be for specific group/classroom - Visibility of the question. It may be possible that the question
	 * is visible to all but some of the answers are not. E.g if the question is answered by an instructor who only wants
	 * the paid students to see the answer.
	 * 
	 * POST event listeners:
	 * 1. If the question is asked to specific users then a notification should go to them.
	 * 2. Add to index for searching. Even the pictures.
	 * 3. Email notifications to the one who posted and to the community that a new question is posted.
	 * 
	 * 
	 * @param user - who posted the question
	 * @param visibility - public or restricted to group
	 * @param classroom - in case this is a restricted question
	 * @param subject - math, physics, chemistry etc
	 * @param tags - like algebra, mechanics etc. Like a sub-category of subject.
	 * @param question content 
	 * 
	 */

	void postQuestion();
	
	
	/**
	 * using this feature one can request somebody to answer this question.
	 * @param user - who posted the question/ should others also be able to ask - probably YES. 
	 * @param professor - who is requested to answer the question
	 */
	void requestionAnswerFrom();
	
	/**
	 * when can the question be updated and who can update
	 * Only the user who 
	 */
	void updateQuestion();
	
	/**
	 * Ability to do the rating of difficulty level - EASY, MEDIUM , TOUGH
	 * Only some privileged users will be able to do so
	 */
	void rateQuestion();
	
	
	/**
	 * Only user who added the question can delete it.
	 */
	void deleteQuestion();
	
	/**
	 * Search fields:
	 * @param text - that needs to be searched
	 * @param by question post date, time
	 * @param posted by someone
	 * @param tag - other info must also be provided along with this. This alone would not yield good results. 
	 * 
	 */
	void searchQuestion();
	
	
	void shareQuestionByEmail();
	
	Question addQuestion( QuestionModel model);

	Question submitAnswer(AnswerModel model);

	Question getQuestionById(Integer questionId);

	void updateStats(Question question);
	
	

}
