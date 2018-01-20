package com.coachqa.ws.controllor;

import com.coachqa.entity.AppUser;
import com.coachqa.entity.Question;
import com.coachqa.enums.QuestionStatusEnum;
import com.coachqa.service.QuestionService;
import com.coachqa.service.UserService;
import com.coachqa.ws.model.AnswerModel;
import com.coachqa.ws.util.WSUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/questions")
public class QuestionControllor {

	@Autowired
	private QuestionService questionService;

	@Autowired
	private UserService userService;


	@PostMapping
	public Question submitQuestion(@RequestBody Question question, HttpServletRequest request, HttpServletResponse response) {

		AppUser user = WSUtil.getUser(userService);


		question.setPostedBy(user);
		question.setRefQuestionStatusId(QuestionStatusEnum.NEW);

		Question addedQuestion = questionService.postQuestion(user.getAppUserId(), question);
		WSUtil.setLocationHeader(request, response, addedQuestion.getQuestionId());


		Integer addedQuestionId = addedQuestion.getQuestionId();
		List<Question> similarQuestionIds = questionService.findSimilarQuestions(addedQuestion, 5);

		return addedQuestion;
	}


	/**
	 * Following items are editable:
	 * 1. Title
	 * 2. Content
	 * 3. Classroom
	 * 4. Tags
	 * <p>
	 * Fields which can't be edited
	 * <p>
	 * <p>
	 * Question can be edited only if it is unanswered. Edit also requires approval.
	 */
	@PutMapping(value = "/{questionId}")
	public void updateQuestion(@PathVariable(value = "questionId") Integer questionId,
							   @RequestBody Question question, HttpServletRequest request, HttpServletResponse response) {
		AppUser user = WSUtil.getUser(userService);

		questionService.updateQuestion(question);

	}

	@RequestMapping(value = "/myquestions", method = RequestMethod.GET)
	public List<Question> getMyQuestions() {
		AppUser user = WSUtil.getUser(userService);
		return questionService.getUsersPosts(user);

	}

	public static void main(String[] args) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		System.out.println(mapper.writeValueAsString(new Date()));
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

	@RequestMapping(value = "/favorite/{questionId}",  method = RequestMethod.POST)
	public void markAsFavorite(@PathVariable(value ="questionId")Integer questionId, @RequestParam boolean isFavorite){
		AppUser user = WSUtil.getUser(userService);
		questionService.markAsFavorite(user.getAppUserId(), questionId, isFavorite);
	}

	@GetMapping(value = "/favorite")
	public List<Question> getMyFavorites(){
		AppUser user = WSUtil.getUser(userService);
		return questionService.getMyFavorites(user.getAppUserId());
	}


}
