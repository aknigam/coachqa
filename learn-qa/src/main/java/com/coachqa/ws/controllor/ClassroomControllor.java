package com.coachqa.ws.controllor;

import com.coachqa.entity.AppUser;
import com.coachqa.entity.Classroom;
import com.coachqa.service.ClassroomService;
import com.coachqa.service.UserService;
import com.coachqa.ws.model.ClassroomMembershipRequest;
import com.coachqa.ws.util.WSUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("/api/classrooms")
public class ClassroomControllor {

	@Autowired
	private ClassroomService classroomService;

	@Autowired
	private UserService userService;

	@ResponseBody
	@PostMapping
	public Classroom createClassroom(@RequestBody Classroom classroom, HttpServletRequest request , HttpServletResponse response){

		classroom.setClassOwner(WSUtil.getUser( userService));
		if(classroom.getSubject() == null) {
			throw new RuntimeException("Classroom must have a subject");
		}
		Classroom newClassroom = classroomService.createClassroom(classroom );

		WSUtil.setLocationHeader(request, response, newClassroom.getClassroomId());
		return newClassroom;
	}

	@ResponseBody
	@PutMapping
	public Classroom updateClassroom(@RequestBody Classroom classroom, HttpServletRequest request , HttpServletResponse
			response){

		if(classroom.getSubject() == null) {
			throw new RuntimeException("Classroom must have a subject");
		}
		Classroom newClassroom = classroomService.createClassroom(classroom );

		WSUtil.setLocationHeader(request, response, newClassroom.getClassroomId());
		return newClassroom;
	}


	@ResponseBody
	@GetMapping(path = "/search")
	public List<Classroom> searchClassrooms(@RequestParam(required = false) Integer page, @RequestParam(required =
			false) boolean onlyLoginUserClassrooms)
	{
		AppUser user = WSUtil.getUser( userService);
		return classroomService.searchClassrooms( user.getAppUserId(),page, onlyLoginUserClassrooms);

	}

	@ResponseBody
	@GetMapping(path = "/id/{classroomId}")
	public Classroom showClassroomById(@PathVariable(value = "classroomId") Integer classroomId)
	{
		return classroomService.getClassroom(classroomId);

	}

	/**
	 * shall be required in case some wants to search classroom by name.
	 * LOW PRIORITY
	 * @param classname
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/name/{name}" , method = RequestMethod.GET)
	public Classroom showClassroomByName(@PathVariable(value = "name") String classname)
	{
		// todo
		return classroomService.getClassroomByName(classname);

	}
	
	/**
	 * This method takes the join classroom request. user will be able to join only of the request gets approved.
	 * Notifications module will be able to show requests.
	 * 
	 * Classroom coordinator will aprove/reject the request.
	 * @return 
	 */
	@RequestMapping(value="/{classroomId}/membership" , method = RequestMethod.POST)
	@ResponseBody
	public void requestClassroomMembership(@PathVariable Integer classroomId, @RequestParam String comments,
										HttpServletRequest request)
	{
		AppUser user = WSUtil.getUser( userService);
		classroomService.requestClassroomMembership(user.getAppUserId(), classroomId, comments);
		return;
	}

	/**
	 *
	 * The user who made this API call should either be the member who is the leaving the class
	 * or the owner of the class;
	 *
	 * This request can be made by the user himself or by the owener of the classroom.
	 *
	 */
	@RequestMapping(value="/{classroomId}/leave" , method = RequestMethod.POST)
	@ResponseBody
	public void requestMembershipTermination(@PathVariable Integer classroomId,
											   @RequestParam Integer requestedByUserId,
											   @RequestParam Integer memberId,
											   @RequestParam String comments,
											   HttpServletRequest request)
	{
		AppUser user = WSUtil.getUser(userService);
		classroomService.leaveClassroom(classroomId, requestedByUserId, memberId, comments);
		return;
	}
	/**
	 * Input should be the list of userIds and action. Action can be approve or reject.
	 * The class owner must be the logged-in user. 
	 */
	@RequestMapping(value="/{classroomId}/processMembershipRequest" , method = RequestMethod.POST)
	@ResponseBody
	public void processJoinRequest(@RequestBody ClassroomMembershipRequest membershipRequests, HttpServletRequest
			request ,HttpServletResponse response ){
		AppUser user = WSUtil.getUser( userService);
		classroomService.processJoinRequest(user, membershipRequests);
		return ;
	}

	@RequestMapping(value="/{classroomId}/membershiprequests", method = RequestMethod.GET)
	public @ResponseBody ClassroomMembershipRequest showJoinRequests(@PathVariable(value = "classroomId") Integer classroomId, HttpServletRequest request){

		AppUser user = WSUtil.getUser(userService);
		ClassroomMembershipRequest membershipRequest = classroomService.getMemberShipRequests(user, classroomId);

		return membershipRequest;
	}


	@RequestMapping(value="/usermemberships", method = RequestMethod.GET)
	public @ResponseBody List<Classroom> getUsermemberships(HttpServletRequest request){

		AppUser user = WSUtil.getUser(userService);
		return classroomService.getUserMemberships(user);

	}



	

	
}
