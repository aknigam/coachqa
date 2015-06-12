package com.coachqa.ws.controllor;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.coachqa.entity.AppUser;
import com.coachqa.entity.Classroom;
import com.coachqa.service.ClassroomService;
import com.coachqa.ws.util.WSUtil;

@Controller
@RequestMapping("/classrooms")
public class ClassroomControllor {

	@Autowired
	private ClassroomService classroomService;
	
	public void createClassroom(){}
	
	/**
	 * This will take the user to home page of the classroom.
	 * Home page must have the option to join/leave.
	 * It will show the list of following type questions:
	 * 	1. Most active
	 * 	2. Unanswered
	 * It will also show the list of class members and class owner.
	 * @return 
	 */
	@RequestMapping(value="/{id}" , method = RequestMethod.GET)
	public ModelAndView showClassroom(@PathVariable(value ="id")Integer classroomId)
	{
		Classroom classroom = classroomService.getClassroom(classroomId);
		ModelMap model =  new ModelMap("classroom", classroom);
		return new ModelAndView("classroom", model);
	}
	
	/**
	 * This method takes the join classroom request. user will be able to join only of the request gets approved.
	 * Notifications module will be able to show requests.
	 * 
	 * Classroom coordinator will aproove/reject the request.
	 * @return 
	 */
	public String joinClassroom(Integer classroomId, HttpServletRequest request)
	{
		AppUser user = WSUtil.getUser(request.getSession());
		classroomService.joinClassroom(user.getAppUserId(), classroomId);
		return "redirect:/classrooms/"+classroomId;
		
	}
	
	/**
	 * Input should be the list of userIds and action. Action can be approve or reject.
	 * The class owner must be the logged-in user. 
	 */
	public void processJoinRequest(){}
	
	public void showJoinRequests(){}
	
	/**
	 * User may be asked to choose from a list of reason codes. List will have the 'OTHER' option where user can enter text.
	 */
	public void leaveClassroom(){}
	

	
}
