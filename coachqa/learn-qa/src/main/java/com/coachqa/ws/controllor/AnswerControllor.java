package com.coachqa.ws.controllor;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/answer")
public class AnswerControllor {
	
	
	@ResponseBody
	@RequestMapping(method = RequestMethod.POST)
	public Object addQuestion(@RequestBody Object model)
	{
		return null;
	}
	
	@ResponseBody
	@RequestMapping(value="/id")
	public void getQuestion()
	{
		
	}
	
	
	

}
