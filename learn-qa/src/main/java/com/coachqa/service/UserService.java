package com.coachqa.service;

import com.coachqa.entity.AppUser;
import com.coachqa.ws.model.UserModel;
import notification.entity.NotificationPreference;

public interface UserService {

	AppUser addUser(UserModel user);

	AppUser getUserDetails(Integer userId);

	AppUser getUserByEmail(String username);

	void addOrUpdateUserNotificationPreference(NotificationPreference preference);
}
