package com.coachqa.service.impl;

import com.coachqa.entity.Account;
import com.coachqa.entity.AndroidToken;
import com.coachqa.entity.AppUser;
import com.coachqa.exception.UserNotFoundException;
import com.coachqa.repository.dao.AccountDAO;
import com.coachqa.repository.dao.UserDAO;
import com.coachqa.service.UserService;
import notification.NotificationService;
import notification.entity.NotificationPreference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.List;


@Component
public class AppUserService implements UserService {

	@Autowired
	private UserDAO userDAO;

	@Autowired
	private AccountDAO accountDao;

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

		if(user.getUserType() == null) {
			throw new RuntimeException("User type must be specified");
		}
		// check of the account exists
		if(user.getAccount() == null || StringUtils.isEmpty(user.getAccount().getAccountName() == null)) {
			throw new RuntimeException("User registration cannot be completed as the provided account does not exist");
		}
		Account account = accountDao.fetchAccountByName(user.getAccount().getAccountName());
		if(account == null) {
			throw new RuntimeException("User registration cannot be completed as the provided account does not exist");
		}
		user.setAccount(account);
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
	public List<Integer> getPublicPostContentApprovers() {

		return userDAO.getPublicPostContentApprovers();
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
