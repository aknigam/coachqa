package com.coachqa.service;

import com.coachqa.entity.AppUser;
import notification.entity.NotificationPreference;

public interface UserService {

	AppUser addUser(AppUser user);

	AppUser getUserDetails(Integer userId);

	AppUser getUserByEmail(String username);

	void addOrUpdateUserNotificationPreference(NotificationPreference preference);
}
