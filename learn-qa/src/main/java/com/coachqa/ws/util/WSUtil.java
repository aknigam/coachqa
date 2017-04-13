package com.coachqa.ws.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.coachqa.entity.AppUser;
import com.coachqa.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.security.Principal;

public class WSUtil {

	private static Logger LOGGER = LoggerFactory.getLogger(WSUtil.class);

	private static final String LOCATION_HEADER = "location";
	private static final String FORWARD_SLASH = "/";

	public static void setLocationHeader(HttpServletRequest request,
			HttpServletResponse response, Integer resourceIdentifier) {
		
		if(resourceIdentifier == null)
			return;
		
		String location = request.getRequestURI().toString() + FORWARD_SLASH + resourceIdentifier;
		response.addHeader(LOCATION_HEADER, location);
		response.setStatus(201);
	}

	public static AppUser getUser( UserService userService) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName();
		return userService.getUserByEmail(username);
	}
}
