package com.coachqa.web.interceptor;

import com.coachqa.entity.AppUser;
import com.coachqa.ws.model.HomePageModel;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Component
public class LearnQARequestInterceptor extends HandlerInterceptorAdapter {

	
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {

		HttpSession session = request.getSession();
		if(session.getAttribute("userId") == null)
		{
			response.sendRedirect("/assess/login.html");
			return false;
		}

		return true;
		
	}
	
	
	//@Override
	public void postHandlea(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
		HttpSession session = request.getSession();
		if(session.getAttribute("userId") == null)
		{
			response.sendRedirect("/login.html");
			return;
		}
		
		if(modelAndView == null)
		{
			return;
			
		}
		AppUser user = (AppUser) request.getSession().getAttribute("userId");
		
		HomePageModel hpm = new HomePageModel();
		
//		hpm.setUsername(user.getName());
		hpm.setUserId(user.getAppUserId());
		modelAndView.addObject("userinfo", hpm);
		super.postHandle(request, response, handler, modelAndView);
	}
	
}
