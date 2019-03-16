package com.coachqa.ws.controllor;

import com.coachqa.entity.AppUser;
import com.coachqa.entity.Classroom;
import com.coachqa.service.ClassroomService;
import com.coachqa.service.UserService;
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

		AppUser user = WSUtil.getUser(userService);
		classroom.setAccount(user.getAccount());
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
		// TODO: 18/02/19
		Classroom newClassroom = classroomService.updateClassroom(classroom );

		WSUtil.setLocationHeader(request, response, newClassroom.getClassroomId());
		return newClassroom;
	}


	@ResponseBody
	@GetMapping(path = "/search")
	public List<Classroom> searchClassrooms(@RequestParam(required = false) Integer page, @RequestParam(required =
			false) boolean onlyLoginUserClassrooms)
	{
		AppUser user = WSUtil.getUser( userService);
		return classroomService.searchClassrooms( user, page, onlyLoginUserClassrooms);

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




	

	
}
