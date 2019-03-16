package com.coachqa.ws.controllor;

import com.coachqa.entity.AndroidToken;
import com.coachqa.entity.AppUser;
import com.coachqa.entity.UserTypeEnum;
import com.coachqa.service.UserService;
import com.coachqa.ws.util.WSUtil;
import notification.entity.NotificationPreference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/api/users")
public class UserControllor {
	
	@Autowired
	private UserService userService;
	/**
	 * Basic validation will be done using spring validation framework
	 * @param request
	 * @param response
	 * @return
	 */


	/*
	this endpoint should be used to create following users
	1 - Account admin - can be done by application_admin
	2 - Classroom admin - can be done by account_admin and classroom_admin
	by the account admins to create classroom admins
	 */
	@PostMapping
	@ResponseBody
	public AppUser addUser( @RequestBody AppUser user ) {

		AppUser addedBy = WSUtil.getUser(userService);

		if(user.getUserType() == UserTypeEnum.account_admin && addedBy.getUserType() != UserTypeEnum.application_admin) {
			throw new RuntimeException("only application admin can add new account users");
		}
		if(user.getUserType() == UserTypeEnum.classroom_admin &&
				!(addedBy.getUserType() == UserTypeEnum.account_admin || addedBy.getUserType() == UserTypeEnum
						.account_admin)) {
			throw new RuntimeException("only classroom adming or account admin can add classroom admins");
		}
		userService.addUser(user);
		return user;

	}


	@ResponseBody
	@GetMapping
	public AppUser getUserDetails() {
		return WSUtil.getUser(userService);
	}

	@ResponseBody
	@RequestMapping(value="/{userId}/notification/preference", method = RequestMethod.POST)
	public void addOrUpdateUserNotificationPreference(@PathVariable("userId") int userId,
													  @RequestBody NotificationPreference preference, HttpServletRequest request, HttpServletResponse response)
	{
		/*
		 * Steps:
		 * 1. register the user
		 */
		userService.addOrUpdateUserNotificationPreference(preference);


	}

	@ResponseBody
	@PostMapping(value="/{userId}/androidtoken")
	public void addAndroidUserToken(@PathVariable("userId") int userId, @RequestBody AndroidToken androidToken)
	{
		androidToken.setAppUserId(userId);
		userService.addAndroidUserToken(androidToken);


	}



	@ResponseBody
	@RequestMapping(value="/{userId}" , method = RequestMethod.GET)
	public AppUser getUserDetails(@PathVariable(value = "userId") Integer userId)
	{
		return userService.getUserDetails(userId);

	}


	@RequestMapping(value="/ping",method = RequestMethod.GET)
	public @ResponseBody String ping(String ping, HttpServletRequest request)
	{

		AppUser user = WSUtil.getUser(userService);
		
		if(user != null)
			return "TRUE"+ user.getFirstName() +" "+ user.getLastName();
		else 
			return "FALSE";
		
	}


	
	
	
}
