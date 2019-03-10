package com.coachqa.repository.dao.mybatis.mapper;

import com.coachqa.entity.Post;
import com.coachqa.entity.PostVote;
import com.coachqa.entity.Question;
import com.coachqa.entity.QuestionVote;
import com.coachqa.enums.PostTypeEnum;
import com.coachqa.repository.dao.mybatis.typehandler.DateTimeTypeHandler;
import com.coachqa.repository.dao.mybatis.typehandler.PostTypeEnumTypeHandler;
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
    @Select("select postId, posttype, postedby, postdate, votes, classroomid , content from post where postid = " +
            "#{postId}")
    @Results({
            @Result(column = "posttype", property= "postTypeEnum", javaType = PostTypeEnum.class,
                    typeHandler = PostTypeEnumTypeHandler.class),
            @Result(column = "postedby", property = "postedBy.appUserId"),
            @Result(column = "classroomid", property = "classroom.classroomId")
    })

    Post getPostById(@Param("postId") Integer postId);
    @Update("Update post set noofviews = noofviews + 1 where postId =  #{postId}")
    void incrementPostViewsQuery(@Param("postId") Integer postId);

    @Update("Update post set votes = votes + #{votes} where postId = #{postId}")
    void adjustVotes(@Param("postId") Integer postId, @Param("votes")  int votes);

    @Insert("insert into postVote (votedbyuserid, postId  ,UpOrDown, VoteDate, posttype) values (#{postedBy},#{postId},#{isUpVote},#{postDate,typeHandler=com.coachqa.repository.dao.mybatis.typehandler.DateTimeTypeHandler},#{postType.type})")
    @Options(useGeneratedKeys=true, keyProperty="postId")
    void postVote(PostVote postVote);

    @Update("Update post set approvalstatus =  #{isApproved} ,  approvalcomment = #{comments}  where postId = #{postId} ")
    void updatePostApproval(PostApproval postApproval);

    @Select("select votedbyuserid, postId  ,UpOrDown, VoteDate from postVote where votedbyuserid = #{userId} order by VoteDate desc limit 1")
    @Results({
            @Result(column = "VoteDate", property = "voteDate", javaType = DateTime.class, typeHandler = DateTimeTypeHandler.class)
    })
    List<QuestionVote> getUserVotedQuestions(Integer userId);


    @Update("Update post set classroomid = #{classroom.classroomId} , approvalstatus =  #{approvalStatus} ,  content " +
            "= " +
            "#{content}  where postId = #{postId} ")
    void updateQuestion(Question updatedQuestion);

    @Insert("insert into post ( postdate, postedby, content, posttype, classroomid, approvalstatus, accountId)  " +
            " values ( #{postDate}, #{postedBy.appUserId}, #{content},  #{postTypeEnum.type}, #{classroom.classroomId}, " +
            " #{approvalStatus} , #{account.accountId})"
    )
    @Options(useGeneratedKeys=true, keyProperty="postId")
    int addPost(Post post);

//    @Select("select postId, posttype, postedby, postdate, votes, classroomid from post where postid = #{postId}")
    @Select("select " +
            "  p.postType, " +
            "  p.postedby, " +
            "  p.postdate, " +
            "  u.firstname, " +
            "  u.middlename, " +
            "  u.lastname, " +
            "  u.email " +
            "  ,CASE WHEN a.answerid IS NULL " +
            "    THEN c.classroomid " +
            "  ELSE ac.classroomid END AS classroomid " +
            "  ,CASE WHEN a.answerid IS NULL " +
            "    THEN c.classname " +
            "  ELSE ac.classname END AS classname " +
            "  ,CASE WHEN a.answerid IS NULL " +
            "    THEN q.questionid " +
            "  ELSE a.questionid END AS postId " +
            " from  post p " +
            "  LEFT JOIN answer a ON a.answerid = p.postid " +
            "  LEFT JOIN question aq on aq.questionid = a.questionid " +
            "  LEFT JOIN classroom ac on ac.classroomid = aq.classroomId " +
            "  LEFT JOIN question q on q.questionid = p.postid " +
            "  LEFT JOIN classroom c on c.classroomid = p.classroomId " +
            "  LEFT JOIN appuser u on u.appuserid = p.postedby " +
            " where p.approvalstatus =0 " +
            " and ((ac.classowner is not null and  ac.classowner = #{appUserId}) " +
            "   or (c.classowner is not null and c.classowner= #{appUserId})) " +
            " order by p.postdate desc limit #{page}, 5 ")
    @Results({

            @Result(column = "postedby", property = "postedBy.appUserId"),
            @Result(column = "posttype", property= "postTypeEnum", javaType = PostTypeEnum.class,
                    typeHandler = PostTypeEnumTypeHandler.class),
            @Result(column = "firstname", property= "postedBy.firstName"),
            @Result(column = "lastName", property= "postedBy.lastName"),
            @Result(column = "middlename", property= "postedBy.middleName"),
            @Result(column = "email", property= "postedBy.email"),
            @Result(column = "classname", property= "classroom.className"),
            @Result(column = "classroomid", property= "classroom.classroomId")
    })
    /*
    Potentially duplicate postId can be returned becuase a post can have multiple answers
    waiting for apporval. Can think of clubbing posts together

     */
    List<Post> getPostsPendingApprovalByUser(@Param("appUserId") Integer appUserId, @Param("page") Integer page);

    @Update("update post_extracted_text set extracted_text=  #{imageExtractedText} where postId = #{postId}")
    void updatePostExtractedtext(@Param("postId") Integer postId, @Param("imageExtractedText") String imageExtractedText);

    @Insert("insert INTO post_extracted_text  (postId, extracted_text) value (#{postId}, #{imageExtractedText}) ")
    void addPostExtractedtext(@Param("postId") Integer postId, @Param("imageExtractedText") String imageExtractedText);

    @Select("select extracted_text from post_extracted_text where postId = #{postId}")
    String extractedTextExists(@Param("postId") Integer postId);
}
