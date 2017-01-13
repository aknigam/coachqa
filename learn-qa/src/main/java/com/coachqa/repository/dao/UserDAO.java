package com.coachqa.repository.dao;

import com.coachqa.entity.AppUser;


public interface UserDAO {

	AppUser getUserByEmail(String userEmail);

	AppUser addUser(AppUser user);

	AppUser getUserByIdentifier(Integer userId);


}
