package com.coachqa.notification;

public class NotificationService {

	
	/**
	 * |=================================================================================================|
	 * |Build the notification service in such a way that it can be used as an independent module.		 |
	 * |=================================================================================================|
	 * 
	 * 
	 * Design:
	 * -------
	 * 
	 * Event
	 * Registered listeners (can be users or other components of the system) get notified when the event occurs.
	 *  
	 * 
	 * 
	 * 
	 * Question notifications
	 * ----------------------
	 * 
	 * When any of the below changes happens in the question then the user who posted gets the notification. Anybody should be 
	 * able to listen to changes register for events in the question.
	 * 
	 * List of question related events:
	 * 1. Question answered.
	 * 2. Question voted or answer voted.
	 * 3. Question viewed.
	 * 
	 * Classroom notifications
	 * -----------------------
	 * 1. Join request to classroom.
	 * 2. Join request approved/rejected.
	 * 
	 * 
	 * 
	 */
}
