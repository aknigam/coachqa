package com.coachqa.ws.controllor;

import com.coachqa.entity.AppUser;
import com.coachqa.enums.PostTypeEnum;
import com.coachqa.enums.QuestionRatingEnum;
import com.coachqa.service.PostService;
import com.coachqa.ws.util.WSUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Both question and answer can be treated as posts. Some common operations like voting, rating etc can be done in a common manner.
 */
@Controller
@RequestMapping("/api/posts")
public class PostControllor {


    @Autowired
    private PostService postService;
    /**
     * No of votes for the question shows people's interest in that particular question.
     * type - can have two values - 1 for question 2 for answer
     */
    @RequestMapping(value="/{type}/{id}/vote/{vote}", method = RequestMethod.POST)
    public void vote(@PathVariable(value ="type")Integer postType,
                     @PathVariable(value ="id")Integer postId,
                     @PathVariable(value ="vote")Integer vote,
                     HttpServletRequest request , HttpServletResponse response) {


        AppUser user = WSUtil.getUser(request.getSession());
        boolean isUpVote = vote > 0 ? true: false;
        postService.vote(user.getAppUserId(), postId, isUpVote, PostTypeEnum.getPostType(postType));
    }


    /**
     * When will the question be rated?
     * A link will be provided next to the question to rate the question.
     *
     * Final rating will be cumalative of rating.
     */
    @RequestMapping(value="/rate/{questionId}", method = RequestMethod.POST)
    public void rate(@PathVariable(value ="questionId")Integer questionId,
                             String rating,
                             HttpServletRequest request , HttpServletResponse response) {

        AppUser user = WSUtil.getUser(request.getSession());
         postService.ratePost(user.getAppUserId(), questionId, QuestionRatingEnum.MEDUIM);
    }


}
