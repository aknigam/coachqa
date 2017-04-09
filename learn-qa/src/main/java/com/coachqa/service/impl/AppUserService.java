package com.coachqa.service.impl;

import notification.NotificationService;
import notification.entity.NotificationPreference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.coachqa.entity.AppUser;
import com.coachqa.repository.dao.UserDAO;
import com.coachqa.service.UserService;

import java.util.Collection;
import java.util.List;


@Component
public class AppUserService implements UserService {

	@Autowired
	private UserDAO userDAO;

	@Autowired
	private NotificationService notificationService;

	/**
	 *
	 * Create the user in the DB and add the default notification preference
	 * TODO: Add user notification preferences
	 */
	@Override
	@Transactional
	public AppUser addUser(AppUser user) {
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

	@Override
	public List<Integer> getPostContentApprovers() {
		return userDAO.getPostContentApprovers();
	}


}
