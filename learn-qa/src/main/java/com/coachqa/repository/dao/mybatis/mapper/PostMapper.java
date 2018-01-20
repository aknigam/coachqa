package com.coachqa.repository.dao.mybatis.mapper;

import com.coachqa.entity.Post;
import com.coachqa.entity.PostVote;
import com.coachqa.entity.Question;
import com.coachqa.entity.QuestionVote;
import com.coachqa.repository.dao.mybatis.typehandler.DateTimeTypeHandler;
import com.coachqa.ws.model.PostApproval;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.joda.time.DateTime;

import java.util.List;

/**
 * Created by a.nigam on 23/07/17.
 */

@Mapper
public interface PostMapper {


    /**
     *  Post p = new Post();
        AppUser au = new AppUser();
        au.setAppUserId(rs.getInt("postedBy"));
        p.setPostedBy(au);
        p.setPostId(postId);
        p.setVotes(rs.getInt("Votes"));

     */
    @Select("select postId, postType, postedBy, postDate, Votes, classroomId from post where postid = #{postId}")
    @Results({

            @Result(column = "postedBy", property = "postedBy.appUserId")
    })

    Post getPostById(@Param("postId") Integer postId);

    @Update("Update Post set NoOfViews = NoOfViews + 1 where postId =  #{postId}")
    void incrementPostViewsQuery(@Param("postId") Integer postId);

    @Update("Update Post set Votes = Votes + #{votes} where postId = #{postId}")
    void adjustVotes(@Param("postId") Integer postId, @Param("votes")  int votes);

    @Insert("insert into PostVote (VotedByUserId, postId  ,UpOrDown, VoteDate, postType) values (#{postedBy},#{postId},#{isUpVote},#{postDate,typeHandler=com.coachqa.repository.dao.mybatis.typehandler.DateTimeTypeHandler},#{postType.type})")
    @Options(useGeneratedKeys=true, keyProperty="postId")
    void postVote(PostVote postVote);

    @Update("Update Post set ApprovalStatus =  #{isApproved} ,  ApprovalComment = #{comments}  where postId = #{postId} ")
    void updatePostApproval(PostApproval postApproval);

    @Select("select VotedByUserId, PostId  ,UpOrDown, VoteDate from PostVote where VotedByUserId = #{userId} order by VoteDate desc limit 1")
    @Results({
            @Result(column = "VoteDate", property = "voteDate", javaType = DateTime.class, typeHandler = DateTimeTypeHandler.class)
    })
    List<QuestionVote> getUserVotedQuestions(Integer userId);


    @Update("Update Post set ClassroomId = #{classroomId} , ApprovalStatus =  #{isApproved} ,  Content = #{content}  where postId = #{approvedBy} ")
    void updateQuestion(Question updatedQuestion);

    @Insert("insert into post ( postdate, PostedBy, Content, posttype, classroomId, ApprovalStatus)  " +
            " values ( now(), #{postedBy.appUserId}, #{content},  #{postTypeEnum.type}, #{classroomId}, #{approvalStatus})"
    )
    @Options(useGeneratedKeys=true, keyProperty="postId")
    int addPost(Post post);
}
