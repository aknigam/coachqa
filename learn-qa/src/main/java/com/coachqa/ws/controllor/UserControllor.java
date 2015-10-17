package com.coachqa.ws.controllor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import com.coachqa.entity.AppUser;
import com.coachqa.service.UserService;
import com.coachqa.ws.model.UserModel;
import com.coachqa.ws.model.builder.UserModelBuilder;
import com.coachqa.ws.util.WSUtil;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

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
	@RequestMapping(value="/{id}" , method = RequestMethod.GET)
	public AppUser getUserDetails(@PathVariable(value = "id") Integer userId)
	{
		return userService.getUserDetails(userId);
//		UserModel userModel = UserModelBuilder.build(appUser);
//		return userModel;
	}


	@RequestMapping(value="/ping",method = RequestMethod.GET)
	public @ResponseBody String ping(String ping, HttpServletRequest request)
	{
		HttpSession session = request.getSession();
		
		if(session.getAttribute("userId")!= null)
			return "TRUE"+ session.getAttribute("userId");
		else 
			return "FALSE";
		
	}


	
	
	
}
