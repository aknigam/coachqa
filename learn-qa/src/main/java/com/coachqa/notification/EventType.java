package com.coachqa.notification;

public enum EventType {

	/**
	 * List of question related events:
	 * 1. Question answered.
	 * 2. Question voted or answer voted.
	 * 3. Question viewed.
	 * 
	 * Classroom notifications
	 * -----------------------
	 * 1. Join request to classroom.
	 * 2. Join request approved/rejected.
	 */
	
	QUESTION_ANSWERED(1),
	QUESTION_DELETED(2),
	QUESTION_VOTED(3),
	QUESTION_VIEWED(4);
	
	private int id;
	
	EventType(int id)
	{
		this.id= id;
	}
	
	
	
}
