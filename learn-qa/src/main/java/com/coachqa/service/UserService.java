package com.coachqa.service;

import com.coachqa.entity.AndroidToken;
import com.coachqa.entity.AppUser;
import notification.entity.NotificationPreference;
import notification.impl.UserDetailService;

import java.util.List;

public interface UserService extends UserDetailService {

	AppUser addUser(AppUser user);

	AppUser getUserDetails(Integer userId);

	AppUser getUserByEmail(String username);

	void addOrUpdateUserNotificationPreference(NotificationPreference preference);

	List<Integer> getPublicPostContentApprovers();

	void addAndroidUserToken(AndroidToken androidToken);
}
