package com.coachqa.ws.controllor;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.coachqa.enums.QuestionRatingEnum;
import com.coachqa.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.coachqa.entity.AppUser;
import com.coachqa.entity.Question;
import com.coachqa.entity.RefSubject;
import com.coachqa.service.QuestionService;
import com.coachqa.ws.model.AnswerModel;
import com.coachqa.ws.model.QuestionModel;
import com.coachqa.ws.util.WSUtil;

@Controller
@RequestMapping("/questions")
public class QuestionControllor {
	
	@Autowired
	private QuestionService questionService;

	@Autowired
	private UserService userService;

	/**
	 * Refer: http://www.baeldung.com/get-user-in-spring-security
	 */
	@RequestMapping(value="/home",method = RequestMethod.GET)
	public ModelAndView home(HttpServletRequest request)
	{
		Principal principal = request.getUserPrincipal();
		String username = principal.getName();

		AppUser appUser = userService.getUserByEmail(username);
		request.getSession().setAttribute("userId", appUser);

		ModelMap model = new ModelMap();

		// myreviews.ftl will be resolved
		return new ModelAndView("home", model);
	}
    /**
     * Steps
     * 1. If the user is not logged in then this action is not allowed and the login intercepter takes care of this step.
     * 2. Get the user details
     * 3. Call the service layer method to add the question
     * 4. Return the added person object and the location header with the link to the added question
     */
	@ResponseBody
	@RequestMapping(value="/ask/submit", method = RequestMethod.POST)
	public Question submitQuestion(QuestionModel model, HttpServletRequest request, HttpServletResponse response)
	{
        AppUser user = WSUtil.getUser(request.getSession(), userService);

		model.setPostedBy(user.getAppUserId());


		List<String> newTags = model.getNewTags();
		// add new tags by making service calls.
		// put the generated tagids in the model and then submit the question.

		Question addedQuestion = questionService.addQuestion(user.getAppUserId(), model);
		WSUtil.setLocationHeader(request, response, addedQuestion.getQuestionId());

		// List<Integer> similarQuestionIds =  questionService.findSimilarQuestions(addedQuestionId, 5);
		
		return addedQuestion;
	}


	@ResponseBody
	@RequestMapping(value="/{id}" , method = RequestMethod.GET)
	public ModelAndView getQuestion(@PathVariable(value ="id")Integer questionId)
	{
		Question question = questionService.getQuestionByIdAndIncrementViewCount(questionId);
		// questionService.updateStats(question);
		ModelMap model = new ModelMap("question", question);
		ModelAndView modelAndView = new ModelAndView("question", model);
		return modelAndView;
	}


    @RequestMapping(value="/ask", method = RequestMethod.GET)
	public ModelAndView getPostQuestionPage(HttpServletRequest request, HttpServletResponse response)
	{
		ModelMap model = new ModelMap();
		List<RefSubject> subjects = new ArrayList<RefSubject>();
		subjects.add(new RefSubject(1, "mathematics", "mathematics"));
		model.put("subjects", subjects);
		return new ModelAndView("questionask", model);
	}
	
	
	@RequestMapping(value="/answer/submit" , method = RequestMethod.POST)
	public String submitAnswer(AnswerModel model, HttpServletRequest request , HttpServletResponse response)
	{

		ObjectMapper om = new ObjectMapper();
		om.writerWithDefaultPrettyPrinter();
		Writer w = new StringWriter();
		try {
			om.writeValue(w, model);
			System.out.println(w.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}

		AppUser user = WSUtil.getUser(request.getSession(), userService);
		model.setAnsweredByUserId(user.getAppUserId());
		
		Question answeredQuestion = questionService.submitAnswer(user.getAppUserId(), model);
		return "redirect:/questions/"+model.getQuestionId();
	}

	@ResponseBody
	@RequestMapping(method = RequestMethod.GET)
	public Object getAllQuestions()
	{
		return null;
	}
	
	
	

}
