package com.coachqa.repository.dao;

import com.coachqa.entity.AppUser;
import com.coachqa.ws.model.UserModel;

public interface UserDAO {

	AppUser getUserByEmail(String userEmail);

	AppUser addUser(UserModel user);

	AppUser getUserByIdentifier(Integer userId);


}
