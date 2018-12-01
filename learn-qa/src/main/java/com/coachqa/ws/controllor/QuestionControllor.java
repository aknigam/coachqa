package com.coachqa.ws.controllor;

import com.coachqa.entity.AppUser;
import com.coachqa.entity.Question;
import com.coachqa.entity.Tag;
import com.coachqa.enums.QuestionStatusEnum;
import com.coachqa.service.QuestionService;
import com.coachqa.service.UserService;
import com.coachqa.ws.model.AnswerModel;
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
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
		question.setPostDate(new Date());

		Question addedQuestion = questionService.postQuestion(user.getAppUserId(), question);
		addedQuestion = questionService.getQuestionById(addedQuestion.getQuestionId());
		WSUtil.setLocationHeader(request, response, addedQuestion.getQuestionId());


		Integer addedQuestionId = addedQuestion.getQuestionId();
		List<Question> similarQuestionIds = questionService.findSimilarQuestions(addedQuestion, 0, user.getAppUserId());

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
		question.setPostedBy(user);
		questionService.updateQuestion(question, user);

	}

	@RequestMapping(value = "/myquestions", method = RequestMethod.GET)
	public List<Question> getMyQuestions(@RequestParam(required = false) Integer page) {
		page = page == null ? 0: page;
		AppUser user = WSUtil.getUser(userService);
		List<Question> questions = questionService.getUsersPosts(user, page);

		return questions;

	}


	/**
	 * http://localhost:8080/api/questions/list?tagId=12&classroomId=1&ownerId=1&subjectId=1&isPublic=true
	 *
	 * @param tagId
	 * @param classroomId
	 * @param ownerId
	 * @param subjectId
	 * @param isPublic
     * @param page
	 * @return
     */
	@RequestMapping( value="/search", method = RequestMethod.GET)
	public List<Question> searchQuestions(
			@RequestParam(required = false) Integer tagId,
			@RequestParam(required = false) Integer classroomId,
			@RequestParam(required = false) Integer ownerId,
			@RequestParam(required = false) Integer subjectId,
			@RequestParam(required = false) Boolean isPublic,
			@RequestParam(required = false) Integer page)
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
			criteria.setTags(Arrays.asList(new Tag[]{new Tag(tagId)}));
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
		page = page == null ? 0: page;
		return questionService.findSimilarQuestions(criteria, page, user.getAppUserId());
	}

	/**
	 * http://localhost:8080/api/questions/list?tagId=12&classroomId=1&ownerId=1&subjectId=1&isPublic=true
	 *String queryStr = "question &postedby:bla&postedby:bla1&subject:bla&subject:bla subject&tag:bla&tag:bla tag &" +
	 "classroom:bla& anser ";

	 * @param query
	 *
	 */
	@RequestMapping( value="/searchbyquery", method = RequestMethod.GET)
	public List<Question> searchQuestions(
			@RequestParam(required = false) String postedby,
			@RequestParam(required = false) String subject,
			@RequestParam(required = false) String tag,
			@RequestParam(required = false) String classroom,
			@RequestParam(required = false) String query,
			@RequestParam(required = false) Integer page)
	{
		AppUser user = WSUtil.getUser( userService);
		QueryCriteria c = new QueryCriteria();
		if(classroom != null && !classroom.trim().isEmpty())	c.setClassroom(classroom);
		if(postedby != null && !postedby.trim().isEmpty())	c.setPostedBy(postedby);
		if(subject != null && !subject.trim().isEmpty())	c.setSubject(subject);
		if(tag != null && !tag.trim().isEmpty())	c.setTag(tag);
		if(query != null && !query.trim().isEmpty())	c.addSimpleQueryParam(query);


		page = page == null ? 0: page;
		return questionService.findByQuery(c, page, user.getAppUserId());

	}

	/*
	Query format

	&postedby:"bla" &subject:"bla" &tag:"bla" &classroom:"bla"
	 */




	@RequestMapping(value="/{questionId}" , method = RequestMethod.GET)
	public Question getQuestion(@PathVariable(value = "questionId") Integer questionId)
	{
		AppUser user = WSUtil.getUser( userService);
		return questionService.getQuestionByIdAndIncrementViewCount(questionId, user);
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
	public List<Question> getMyFavorites(@RequestParam(required = false) Integer page){
		page = page == null ? 0: page;
		AppUser user = WSUtil.getUser(userService);
		return questionService.getMyFavorites(user.getAppUserId(), page);
	}


}
