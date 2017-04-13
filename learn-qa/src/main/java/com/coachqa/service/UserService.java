package com.coachqa.service;

import com.coachqa.entity.AppUser;
import notification.entity.NotificationPreference;

import java.util.Collection;
import java.util.List;

public interface UserService {

	AppUser addUser(AppUser user);

	AppUser getUserDetails(Integer userId);

	AppUser getUserByEmail(String username);

	void addOrUpdateUserNotificationPreference(NotificationPreference preference);

	List<Integer> getPostContentApprovers();
}
