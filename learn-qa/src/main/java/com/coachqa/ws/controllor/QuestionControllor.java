package com.coachqa.ws.controllor;

import com.coachqa.entity.AppUser;
import com.coachqa.entity.Question;
import com.coachqa.entity.RefQuestionStatus;
import com.coachqa.enums.QuestionStatusEnum;
import com.coachqa.service.QuestionService;
import com.coachqa.service.UserService;
import com.coachqa.ws.model.AnswerModel;
import com.coachqa.ws.util.WSUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/questions")
public class QuestionControllor {
	
	@Autowired
	private QuestionService questionService;

	@Autowired
	private UserService userService;


	@PostMapping
	public Question submitQuestion(@RequestBody Question question, HttpServletRequest request, HttpServletResponse response)
	{

		AppUser user = WSUtil.getUser(userService);


		question.setPostedBy(user);
		question.setRefQuestionStatusId(QuestionStatusEnum.NEW);

		Question addedQuestion = questionService.postQuestion(user.getAppUserId(), question);
		WSUtil.setLocationHeader(request, response, addedQuestion.getQuestionId());


		Integer addedQuestionId = addedQuestion.getQuestionId();
		List<Question> similarQuestionIds =  questionService.findSimilarQuestions(addedQuestion, 5);

		return addedQuestion;
	}


	/**
	 * Following items are editable:
	 * 1. Title
	 * 2. Content
	 * 3. Classroom
	 * 4. Tags
	 *
	 * Fields which can't be edited
	 *
	 *
	 * Question can be edited only if it is unanswered. Edit also requires approval.
	 */
	@PutMapping(value="/{questionId}" )
	public void updateQuestion(@PathVariable(value ="questionId")Integer questionId ,
								 @RequestBody Question question, HttpServletRequest request, HttpServletResponse response)
	{
		AppUser user = WSUtil.getUser(userService);

		questionService.updateQuestion(question);

	}


	/**
	 * http://localhost:8080/api/questions/list?tagId=12&classroomId=1&ownerId=1&subjectId=1&isPublic=true
	 *
	 * @param tagId
	 * @param classroomId
	 * @param ownerId
	 * @param subjectId
	 * @param isPublic
     * @return
     */
	@RequestMapping( value="/search", method = RequestMethod.GET)
	public List<Question> searchQuestions(
			@RequestParam(required = false) Integer tagId,
			@RequestParam(required = false)  Integer classroomId,
			@RequestParam(required = false)  Integer ownerId,
			@RequestParam(required = false)  Integer subjectId,
			@RequestParam(required = false)  Boolean isPublic
	)
	{
		AppUser user = WSUtil.getUser( userService);

		/*
		subject, class, tag , postedby , isPublic
		 */
		Question criteria = new Question();
		if(subjectId != null)
			criteria.setRefSubjectId(subjectId);
		if(classroomId != null)
			criteria.setClassroomId(classroomId);
		if(tagId != null)
			criteria.setTags(Arrays.asList(new Integer[]{tagId}));
		if(isPublic != null)
			criteria.setPublicQuestion(isPublic);
		if(ownerId != null){
			criteria.setPostedBy(new AppUser(ownerId, "", "", "", ""));
		}

		/*
		TODO
		WARNING : if none of the query params are provided then it can lead to loading of all questions in memory.
		If pagination is implemented then this won't happen though.
		 */

		return questionService.findSimilarQuestions(criteria, 10);
	}

	@RequestMapping(value="/{questionId}" , method = RequestMethod.GET)
	public Question getQuestion(@PathVariable(value = "questionId") Integer questionId)
	{
		AppUser user = WSUtil.getUser( userService);
		return questionService.getQuestionByIdAndIncrementViewCount(questionId);
	}



	@RequestMapping(value="/{questionId}/answer" , method = RequestMethod.POST)
	public Question submitAnswer(@PathVariable(value ="questionId")Integer questionId , @RequestBody AnswerModel model, HttpServletRequest request, HttpServletResponse response)
	{

		AppUser user = WSUtil.getUser(userService);
		model.setAnsweredByUserId(user.getAppUserId());
		model.setQuestionId(questionId);

		return questionService.postAnswer(user.getAppUserId(), model);

	}


	// TODO
	@RequestMapping(value = "/{questionId}/requestanswer",  method = RequestMethod.POST)
	public void requestToAnswer(@PathVariable(value ="questionId")Integer questionId, List<Integer> userIds){

	}


}
