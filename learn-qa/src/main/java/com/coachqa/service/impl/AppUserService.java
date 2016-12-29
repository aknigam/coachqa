package com.coachqa.service.impl;

import notification.NotificationService;
import notification.entity.NotificationPreference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.coachqa.entity.AppUser;
import com.coachqa.repository.dao.UserDAO;
import com.coachqa.service.UserService;
import com.coachqa.ws.model.UserModel;

@Component
public class AppUserService implements UserService {

	@Autowired
	private UserDAO userDAO;

	@Autowired
	private NotificationService notificationService;

	/**
	 *
	 * TODO: Add user notification preferences
	 */
	@Override
	@Transactional
	public AppUser addUser(UserModel user) {


		return userDAO.addUser(user);
	}

	@Override
	public AppUser getUserDetails(Integer userId) {
		return userDAO.getUserByIdentifier(userId);
	}
	
	@Override
	@Transactional
	public AppUser getUserByEmail(String userEmail) {
		return userDAO.getUserByEmail(userEmail);
	}

	@Override
	public void addOrUpdateUserNotificationPreference(NotificationPreference preference) {
		notificationService.setNotificationPreference(preference);
	}


}
