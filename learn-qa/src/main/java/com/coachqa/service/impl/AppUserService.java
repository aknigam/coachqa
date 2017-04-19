package com.coachqa.service.impl;

import com.coachqa.entity.AndroidToken;
import com.coachqa.entity.AppUser;
import com.coachqa.exception.UserNotFoundException;
import com.coachqa.repository.dao.UserDAO;
import com.coachqa.service.UserService;
import notification.NotificationService;
import notification.entity.NotificationPreference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
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

	@Override
	public void addAndroidUserToken(AndroidToken androidToken) {
		userDAO.addAndroidUserToken(androidToken);
	}


	@Override
	public List<String> getAndroidTokens(Integer userId) {
		return userDAO.getAndroidTokens(userId);
	}

	@Override
	public List<String> getEmailAddresses(Integer userId) {
		AppUser user = userDAO.getUserByIdentifier(userId);
		if(user != null){
			return Arrays.asList(user.getEmail());
		}
		throw new UserNotFoundException("User with id ["+userId+"] does not exists.");
	}
}
