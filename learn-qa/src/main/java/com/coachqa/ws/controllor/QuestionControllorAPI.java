package com.coachqa.ws.controllor;

import com.coachqa.entity.AppUser;
import com.coachqa.entity.Question;
import com.coachqa.enums.QuestionRatingEnum;
import com.coachqa.service.QuestionService;
import com.coachqa.service.UserService;
import com.coachqa.ws.model.AnswerModel;
import com.coachqa.ws.model.QuestionModel;
import com.coachqa.ws.util.WSUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("/api/questions")
public class QuestionControllorAPI {
	
	@Autowired
	private QuestionService questionService;

	@Autowired
	private UserService userService;


	@RequestMapping(value="/ask/submit", method = RequestMethod.POST)
	public Question submitQuestion(@RequestBody QuestionModel question, HttpServletRequest request, HttpServletResponse response)
	{
		AppUser user = WSUtil.getUser(request.getSession(), userService);

		question.setPostedBy(user.getAppUserId());


		List<String> newTags = question.getNewTags();
		// add new tags by making service calls.
		// put the generated tagids in the question and then submit the question.

		Question addedQuestion = questionService.addQuestion(user.getAppUserId(), question);
		WSUtil.setLocationHeader(request, response, addedQuestion.getQuestionId());


		Integer addedQuestionId = addedQuestion.getQuestionId();
		List<Question> similarQuestionIds =  questionService.findSimilarQuestions(addedQuestion, 5);

		return addedQuestion;
	}

    @RequestMapping(value="/rate/{questionId}", method = RequestMethod.POST)
    public void rateQuestion(@PathVariable(value ="questionId")Integer questionId,
                           String rating,
                           HttpServletRequest request , HttpServletResponse response) {

        AppUser user = WSUtil.getUser(request.getSession(), userService);
        questionService.rateQuestion(user.getAppUserId(), questionId, QuestionRatingEnum.MEDUIM);
    }

	@RequestMapping( method = RequestMethod.GET)
	public List<Question> getQuestions()
	{
		return questionService.getQuestionsByTag(1);
	}

	@RequestMapping(value="/{id}" , method = RequestMethod.GET)
	public Question getQuestion(@PathVariable(value = "id") Integer questionId)
	{
		return questionService.getQuestionByIdAndIncrementViewCount(questionId);
	}



	@RequestMapping(value="/{id}" , method = RequestMethod.PUT)
	public Object updateQuestion(@PathVariable(value ="id")Integer questionId , @RequestBody Object model)
	{
		return null;
	}


	@RequestMapping(value="/answer/submit" , method = RequestMethod.POST)
	public Question submitAnswer(@RequestBody AnswerModel model, HttpServletRequest request, HttpServletResponse response)
	{

		AppUser user = WSUtil.getUser(request.getSession(), userService);
		model.setAnsweredByUserId(user.getAppUserId());

		return questionService.submitAnswer(user.getAppUserId(), model);

	}



	@RequestMapping(value="/tag/{tagId}", method = RequestMethod.GET)
	public Object getAllQuestions(@RequestParam("tagId") int tagId)
	{
		return questionService.getQuestionsByTag(tagId);
	}
	
	
	@RequestMapping(value = "/request",  method = RequestMethod.POST)
	public void requestToAnswer(Integer questionId, List<Integer> userIds){

	}


}
