package com.coachqa.repository.dao;

import com.coachqa.entity.AndroidToken;
import com.coachqa.entity.AppUser;

import java.util.List;


public interface UserDAO {

	AppUser getUserByEmail(String userEmail);

	AppUser addUser(AppUser user);

	AppUser getUserByIdentifier(Integer userId);


	List<Integer> getPublicPostContentApprovers();

	void addAndroidUserToken(AndroidToken androidToken);

	List<String> getAndroidTokens(Integer userId);

    void addAndroidToken(String androidToken, Integer appUserId);
}
