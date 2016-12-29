package com.coachqa.ws.controllor;

import com.coachqa.entity.AppUser;
import com.coachqa.service.UserService;
import com.coachqa.ws.model.UserModel;
import com.coachqa.ws.util.WSUtil;
import notification.entity.NotificationPreference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
	@RequestMapping(value="/register", method = RequestMethod.POST)
	public AppUser register(@RequestBody UserModel user, HttpServletRequest request, HttpServletResponse response)
	{
		/*
		 * Steps:
		 * 1. register the user
		 */
		AppUser newUser = userService.addUser(user);
		WSUtil.setLocationHeader(request, response, newUser.getAppUserId());
		
		return newUser;
	}

	@ResponseBody
	@RequestMapping(value="/notificationpreference", method = RequestMethod.POST)
	public void addOrUpdateUserNotificationPreference(@RequestBody NotificationPreference preference, HttpServletRequest request, HttpServletResponse response)
	{
		/*
		 * Steps:
		 * 1. register the user
		 */
		userService.addOrUpdateUserNotificationPreference(preference);

	}



	@ResponseBody
	@RequestMapping(value="/{id}" , method = RequestMethod.GET)
	public AppUser getUserDetails(@PathVariable(value = "id") Integer userId)
	{
		return userService.getUserDetails(userId);

	}


	@RequestMapping(value="/ping",method = RequestMethod.GET)
	public @ResponseBody String ping(String ping, HttpServletRequest request)
	{

		AppUser user = WSUtil.getUser(request, userService);
		
		if(user != null)
			return "TRUE"+ user.getFirstName() +" "+ user.getLastName();
		else 
			return "FALSE";
		
	}


	
	
	
}
