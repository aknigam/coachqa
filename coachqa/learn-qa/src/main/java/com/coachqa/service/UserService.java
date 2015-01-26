package com.coachqa.service;

import com.coachqa.entity.AppUser;
import com.coachqa.ws.model.UserModel;

public interface UserService {

	AppUser addUser(UserModel user);

	AppUser getUserDetails(Integer userId);

	AppUser getUserByEmail(String username);

}
