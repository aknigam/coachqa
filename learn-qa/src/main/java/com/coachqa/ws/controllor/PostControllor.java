package com.coachqa.ws.controllor;

import com.coachqa.entity.AppUser;
import com.coachqa.entity.PostApprovalRequest;
import com.coachqa.enums.PostTypeEnum;
import com.coachqa.enums.QuestionRatingEnum;
import com.coachqa.service.PostService;
import com.coachqa.service.UserService;
import com.coachqa.ws.model.PostApproval;
import com.coachqa.ws.util.WSUtil;
import notification.entity.UserEventNotification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Both question and answer can be treated as posts. Some common operations like voting, rating etc can be done in a common manner.
 */
@RestController
@RequestMapping("/api/posts")
public class PostControllor {



    @Autowired
    private UserService userService;

    @Autowired
    private PostService postService;


    /**
     * No of votes for the question shows people's interest in that particular question.
     * type - can have two values - 1 for question 2 for answer
     */
    @RequestMapping(value="/{postId}/{type}/vote/{vote}", method = RequestMethod.POST)
    public void vote(@PathVariable(value ="type")Integer postType,
                     @PathVariable(value ="postId")Integer postId,
                     @PathVariable(value ="vote")Integer vote,
                     HttpServletRequest request , HttpServletResponse response) {


        AppUser user = WSUtil.getUser(userService);
        boolean isUpVote = vote > 0 ? true: false;
        postService.vote(user.getAppUserId(), postId, isUpVote, PostTypeEnum.getPostType(postType));
    }


    /**
     * When will the question be rated?
     * A link will be provided next to the question to rate the question.
     *
     * Final rating will be cumalative of rating.
     */
    @RequestMapping(value="/rate/{postId}", method = RequestMethod.POST)
    public void rate(@PathVariable(value ="{postId}")Integer postId,
                             String rating,
                             HttpServletRequest request , HttpServletResponse response) {

        AppUser user = WSUtil.getUser( userService);
        postService.ratePost(user.getAppUserId(), postId, QuestionRatingEnum.MEDUIM);
    }


    @RequestMapping(value="/{postId}", method = RequestMethod.DELETE)
    public void deletePost(@PathVariable(value ="{postId}")Integer postId,
                     HttpServletRequest request , HttpServletResponse response) {

        AppUser user = WSUtil.getUser( userService);
        postService.deletePost(postId);
    }



    @RequestMapping(value="/approval/{postId}/{isApproved}", method = RequestMethod.POST)
    public void processApprovalRequest(
                     @PathVariable(value ="postId")Integer postId,
                     @PathVariable(value ="isApproved")Integer approve) {

        AppUser user = WSUtil.getUser(userService);
        boolean isApproved = approve > 0 ? true: false;

        PostApproval postApproval = new PostApproval();
        postApproval.setApproved(isApproved);
        postApproval.setApprovedBy(user);
        postApproval.setPostId(postId);

        postService.updateApprovalStatus(postApproval);
    }


    @RequestMapping(value="/approvals", method = RequestMethod.GET)
    public List<PostApprovalRequest> alerts(@RequestParam(required = true) Integer page){
        AppUser user = WSUtil.getUser(userService);
        // this will have info about event source and the event type. based on this one has to show the content


        // find all the posts waiting for approval
        // find all the membership requests waiting for approval
        return  postService.getPostsPendingApproval(user.getAppUserId(), page);



    }


}
