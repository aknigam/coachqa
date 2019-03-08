package com.coachqa.ws.controllor;

import com.coachqa.entity.AppUser;
import com.coachqa.entity.Classroom;
import com.coachqa.service.ClassroomService;
import com.coachqa.service.UserService;
import com.coachqa.ws.model.ClassroomMembership;
import com.coachqa.ws.util.WSUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/api/classroommembership")
public class ClassroomApprovalControllor {

	@Autowired
	private ClassroomService classroomService;

	@Autowired
	private UserService userService;

	/**
	 * This method takes the join classroom request. user will be able to join only of the request gets approved.
	 * Notifications module will be able to show requests.
	 * 
	 * Classroom coordinator will aprove/reject the request.
	 * @return 
	 */

	// TODO: 06/12/18 this method should take the membership request object
	@PostMapping
	public void requestClassroomMembership(@RequestBody ClassroomMembership membership)
	{
		AppUser user = WSUtil.getUser( userService);
		classroomService.requestClassroomMembership(user, membership.getClassroomId(),  membership
				.getRequestComments());
		return;
	}

	/**
	 *
	 * The user who made this API call should either be the member who is the leaving the class
	 * or the owner of the class;
	 *
	 * This request can be made by the user himself or by the owener of the classroom.
	 * // TODO: 06/12/18 should we also ask from reason
	 *  LEAVE DOESN'T need approval
	 *
	 */
	@RequestMapping(value="/{membershipId}/leave" , method = RequestMethod.POST)
	@ResponseBody
	public void requestMembershipTermination(@RequestParam int membershipId)
	{
		AppUser user = WSUtil.getUser(userService);
		classroomService.leaveClassroom(membershipId, user.getAppUserId());
		return;
	}
	/**
	 * Input should be the list of userIds and action. Action can be approve or reject.
	 * The class owner must be the logged-in user.
	 *
	 * User selects multiple request and APPROVE or DENY
	 *
	 */
	@RequestMapping(value="/processjoinrequests/{isApproved}" , method = RequestMethod.POST)
	@ResponseBody
	public void processJoinRequests(@RequestBody List<Integer> membershipIds, @PathVariable boolean isApproved ){
		AppUser user = WSUtil.getUser( userService);
		classroomService.processJoinRequest(user, membershipIds, isApproved);
		return ;
	}

	/**
	 * If classroom Id is provided then join request for the logged in user for the given classroom are returned
	 * Otherwise, all requests for the logged in user are returned.
	 * @param classroomId
	 * @return
	 */
	@GetMapping("/approvals")
	public @ResponseBody
	List<ClassroomMembership> showJoinRequests(@RequestParam (value = "classroomId", required = false) Integer classroomId,
											   @RequestParam (value = "page", required = true) Integer page){

		AppUser user = WSUtil.getUser(userService);
		List<ClassroomMembership> membershipRequest = classroomService.getMemberShipRequests(user, classroomId, page);
		return membershipRequest;
	}


	@RequestMapping(value="/usermemberships", method = RequestMethod.GET)
	public @ResponseBody List<Classroom> getUsermemberships(HttpServletRequest request){

		AppUser user = WSUtil.getUser(userService);
		return classroomService.getUserMemberships(user);

	}

	
}
