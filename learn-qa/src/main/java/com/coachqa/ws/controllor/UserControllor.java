package com.coachqa.ws.controllor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.coachqa.entity.AppUser;
import com.coachqa.service.UserService;
import com.coachqa.ws.model.UserModel;
import com.coachqa.ws.model.builder.UserModelBuilder;
import com.coachqa.ws.util.WSUtil;

@Controller
@RequestMapping("/users")
public class UserControllor {
	
	@Autowired
	private UserService userService;
	/**
	 * Basic validation will be done using spring validation framework
	 * @param model
	 * @param request
	 * @param response
	 * @return 
	 */
	@RequestMapping(value="/register", method = RequestMethod.POST)
	public UserModel register( UserModel user, HttpServletRequest request , HttpServletResponse response)
	{
		/*
		 * Steps:
		 * 1. register the user
		 */
		AppUser newUser = userService.addUser(user);
		WSUtil.setLocationHeader(request, response, newUser.getAppUserId());
		
		return user;
	}
	
	@RequestMapping(value="/{id}" , method = RequestMethod.GET)
	public UserModel getUserDetails(@PathVariable(value ="id")Integer userId)
	{
		AppUser appUser = userService.getUserDetails(userId);
		UserModel userModel = UserModelBuilder.build(appUser);
		return userModel;
	}
	

	
	@RequestMapping(value="/login",method = RequestMethod.POST)
	public ModelAndView login(String username, HttpServletRequest request)
	{
		
		HttpSession session = request.getSession(true);
		AppUser appUser = null;
		if(session.getAttribute("userId")!= null)
		{
			appUser =  (AppUser) session.getAttribute("userId");
		}
		else
		{

			appUser = userService.getUserByEmail(username);
			session.setAttribute("userId", appUser);


		}
		ModelMap model = new ModelMap();
		  
		// myreviews.ftl will be resolved
		return new ModelAndView("home", model);
	}
	
	@RequestMapping(value="/logout",method = RequestMethod.GET)
	public ModelAndView logout( HttpServletRequest request)
	{
		
		HttpSession session = request.getSession(true);
		
		if(session.getAttribute("userId")!= null)
		{
			session.invalidate();
		}
		
		return new ModelAndView("login");
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
