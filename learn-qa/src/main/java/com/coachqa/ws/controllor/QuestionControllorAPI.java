package com.coachqa.ws.controllor;

import com.coachqa.entity.AppUser;
import com.coachqa.entity.Question;
import com.coachqa.entity.RefSubject;
import com.coachqa.enums.QuestionRatingEnum;
import com.coachqa.service.QuestionService;
import com.coachqa.service.UserService;
import com.coachqa.ws.model.AnswerModel;
import com.coachqa.ws.model.QuestionModel;
import com.coachqa.ws.util.WSUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/api/questions")
public class QuestionControllorAPI {
	
	@Autowired
	private QuestionService questionService;

	@Autowired
	private UserService userService;

	@ResponseBody
	@RequestMapping(value="/ask/submit", method = RequestMethod.POST)
	public Question submitQuestion(@RequestBody QuestionModel model, HttpServletRequest request, HttpServletResponse response)
	{
		AppUser user = WSUtil.getUser(request.getSession(), userService);

		model.setPostedBy(user.getAppUserId());


		List<String> newTags = model.getNewTags();
		// add new tags by making service calls.
		// put the generated tagids in the model and then submit the question.

		Question addedQuestion = questionService.addQuestion(user.getAppUserId(), model);
		WSUtil.setLocationHeader(request, response, addedQuestion.getQuestionId());


		Integer addedQuestionId = addedQuestion.getQuestionId();
		List<Integer> similarQuestionIds =  questionService.findSimilarQuestions(addedQuestionId, 5);

		return addedQuestion;
	}

    @RequestMapping(value="/rate/{questionId}", method = RequestMethod.POST)
    public void rateQuestion(@PathVariable(value ="questionId")Integer questionId,
                           String rating,
                           HttpServletRequest request , HttpServletResponse response) {

        AppUser user = WSUtil.getUser(request.getSession(), userService);
        questionService.rateQuestion(user.getAppUserId(), questionId, QuestionRatingEnum.MEDUIM);
    }

	@ResponseBody
	@RequestMapping(value="/{id}" , method = RequestMethod.GET)
	public Question getQuestion(@PathVariable(value = "id") Integer questionId)
	{
		return questionService.getQuestionByIdAndIncrementViewCount(questionId);
	}


	@ResponseBody
	@RequestMapping(value="/{id}" , method = RequestMethod.PUT)
	public Object updateQuestion(@PathVariable(value ="id")Integer questionId , @RequestBody Object model)
	{
		return null;
	}

	@ResponseBody
	@RequestMapping(value="/answer/submit" , method = RequestMethod.POST)
	public Question submitAnswer(@RequestBody AnswerModel model, HttpServletRequest request, HttpServletResponse response)
	{

		AppUser user = WSUtil.getUser(request.getSession(), userService);
		model.setAnsweredByUserId(user.getAppUserId());

		return questionService.submitAnswer(user.getAppUserId(), model);

	}


	@ResponseBody
	@RequestMapping(method = RequestMethod.GET)
	public Object getAllQuestions(@RequestParam("tag") int tagId)
	{
		return questionService.getQuestionsByTag(tagId);
	}
	
	
	@RequestMapping(value = "/request",  method = RequestMethod.POST)
	public void requestToAnswer(Integer questionId, List<Integer> userIds){

	}


}
