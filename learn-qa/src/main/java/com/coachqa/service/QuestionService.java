package com.coachqa.service;

import com.coachqa.entity.AppUser;
import com.coachqa.entity.Question;
import com.coachqa.enums.QuestionRatingEnum;
import com.coachqa.ws.controllor.QueryCriteria;
import com.coachqa.ws.model.AnswerModel;

import java.util.List;

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
	 *   user - who posted the question
	 * visibility - public or restricted to group
	 * classroom - in case this is a restricted question
	 * subject - math, physics, chemistry etc
	 * tags - like algebra, mechanics etc. Like a sub-category of subject.
	 * question content g
	 * model
	 * 
	 */

	//void postQuestion();
	Question postQuestion(Integer userId, Question model);
	
	
	/**
	 * using this feature one can request somebody to answer this question.
	 * user - who posted the question/ should others also be able to ask - probably YES. 
	 * professor - who is requested to answer the question
	 */
	void requestionAnswerFrom(Integer userId, Integer questionId, List<String> users);
	
	/**
	 * when can the question be updated and who can update
	 * Only the user who 
	 */
	void updateQuestion(Question updatedQuestion, AppUser user);
	
	/**
	 * Ability to do the rating of difficulty level - EASY, MEDIUM , TOUGH
	 * Only some privileged users will be able to do so
	 */
	void rateQuestion(Integer userId, Integer questionId, QuestionRatingEnum meduim);
	
	
	/**
	 * Only user who added the question can delete it.
	 */
	void deleteQuestion(Integer userId, Integer questionId);
	
	/**
	 * Search fields:
	 * text - that needs to be searched
	 * by question post date, time
	 * posted by someone
	 * tag - other info must also be provided along with this. This alone would not yield good results. 
	 * 
	 */
	void searchQuestion();
	
	
	void shareQuestionByEmail(Integer questionId);
	


	Question postAnswer(Integer userId, AnswerModel model);

	Question getQuestionById(Integer questionId);

	Question getQuestionByIdAndIncrementViewCount(Integer questionId, AppUser user);


	List<Question> findSimilarQuestions(Question questionId, int noOfResults, AppUser user);

	List<Question> getQuestionsByTag(int tagId);


	List<Question> getUsersPosts(AppUser user, Integer page);

	void markAsFavorite(Integer appUserId, Integer questionId, boolean isFavorite);

	List<Question> getMyFavorites(Integer appUserId, Integer page);

	List<Question> findByQuery(QueryCriteria searchQuery, Integer page, AppUser loggedInUser);
}
