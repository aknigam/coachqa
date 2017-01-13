package com.coachqa.ws.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.coachqa.entity.AppUser;
import com.coachqa.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.Principal;

public class WSUtil {

	private static Logger LOGGER = LoggerFactory.getLogger(WSUtil.class);

	private static final String LOCATION_HEADER = "location";
	private static final String FORWARD_SLASH = "/";

	public static AppUser getUser(HttpSession session, UserService userService) {
		String username = (String) session.getAttribute("username");
		username= "anigam@expedia.com";
		if(username!=null && session.getAttribute("userId") == null){
			// remove this hardcoding once the user identification is done.
			AppUser appUser = userService.getUserByEmail(username);
			session.setAttribute("userId", appUser);
		}
		return (AppUser) session.getAttribute("userId") ;
	}

	public static AppUser getUser(HttpServletRequest request, UserService userService) {

		String username = request.getHeader("username");
		if(username ==  null){
			username =  "anigam@expedia.com";
			LOGGER.warn("Username header is missing. Using the default user.");
		}
		return userService.getUserByEmail(username);

	}

	public static void setLocationHeader(HttpServletRequest request,
			HttpServletResponse response, Integer resourceIdentifier) {
		
		if(resourceIdentifier == null)
			return;
		
		String location = request.getRequestURI().toString() + FORWARD_SLASH + resourceIdentifier;
		response.addHeader(LOCATION_HEADER, location);
		response.setStatus(201);
	}

	public static AppUser getUser(Principal principal, UserService userService) {
		String username = principal.getName();
		return userService.getUserByEmail(username);
	}
}
