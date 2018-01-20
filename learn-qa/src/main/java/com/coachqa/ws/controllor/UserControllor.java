package com.coachqa.ws.controllor;

import com.coachqa.entity.AndroidToken;
import com.coachqa.entity.AppUser;
import com.coachqa.service.UserService;
import com.coachqa.ws.util.WSUtil;
import notification.entity.NotificationPreference;
import notification.enums.NotificationTypeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
	@ResponseBody
	@PostMapping
	public AppUser register(@RequestBody AppUser user, HttpServletRequest request, HttpServletResponse response)
	{
		/*
		 * Steps:
		 * 1. register the user
		 */
		AppUser newUser = userService.addUser(user);
		// todo save the user's notification preference
		NotificationPreference preference = new NotificationPreference(newUser.getAppUserId(), NotificationTypeEnum.APP);
//		If the preference is not set then notification system will use the defualt pref type.
//		userService.addOrUpdateUserNotificationPreference(preference);
		WSUtil.setLocationHeader(request, response, newUser.getAppUserId());
		
		return newUser;
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
