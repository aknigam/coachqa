package com.coachqa.service;

public interface AnswerService {
	
	/**
	 * return all the answers for the given question.
	 * Should this method be in question service.
	 * 
	 */
	void getAnswersForQuestion();
	
	/**
	 * 
	 */
	void addComment();
	
	/**
	 * 
	 */
	void voteUp();
	
	/**
	 * 
	 */
	void addAnswerToQuestion();
	
	

}
