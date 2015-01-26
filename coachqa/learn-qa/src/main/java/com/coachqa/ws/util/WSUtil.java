package com.coachqa.ws.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.coachqa.entity.AppUser;

public class WSUtil {

	private static final String LOCATION_HEADER = "location";
	private static final String FORWARD_SLASH = "/";

	public static AppUser getUser(HttpSession session) {
		
		return (AppUser) session.getAttribute("userId") ;
	}

	public static void setLocationHeader(HttpServletRequest request,
			HttpServletResponse response, Integer resourceIdentifier) {
		
		if(resourceIdentifier == null)
			return;
		
		String location = request.getRequestURI().toString() + FORWARD_SLASH + resourceIdentifier;
		response.addHeader(LOCATION_HEADER, location);
		response.setStatus(201);
	}

}
