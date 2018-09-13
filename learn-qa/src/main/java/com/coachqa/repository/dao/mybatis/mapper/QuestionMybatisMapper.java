package com.coachqa.repository.dao.mybatis.mapper;

import com.coachqa.entity.Answer;
import com.coachqa.entity.Question;
import com.coachqa.enums.PostTypeEnum;
import com.coachqa.enums.QuestionLevelEnum;
import com.coachqa.enums.QuestionStatusEnum;
import com.coachqa.repository.dao.mybatis.typehandler.DateTimeTypeHandler;
import com.coachqa.repository.dao.mybatis.typehandler.PostTypeEnumTypeHandler;
import com.coachqa.repository.dao.mybatis.typehandler.QuestionLevelEnumTypeHandler;
import com.coachqa.repository.dao.mybatis.typehandler.QuestionStatusEnumTypeHandler;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.Date;
import java.util.List;


@Mapper
public interface QuestionMybatisMapper {

    @Update("Update question set refsubjectid = #{refSubjectId} , title =  #{title} ,  ispublic = #{publicQuestion}  " +
            " where questionid = #{questionId} ")
    void updateQuestion(Question updatedQuestion);

    @Insert("insert into question (questionid, refsubjectid, questionlevelid, refquestionstatusid , " +
            " lastactivedate, title, isPublic) " +
            "values (#{postId},#{refSubjectId},1, #{statusId} ,  now(), #{title}, #{publicQuestion} )")
    void addQuestion(Question question);
    @Select({"select " +
            "questionid," +
            "refsubjectid," +
            "questionlevelid," +
            "p.postedby," +
            "p.posttype," +
            "u.firstname," +
            "u.middlename," +
            "u.lastName," +
            "refquestionstatusid," +
            "title," +
            "p.content," +
            "p.noofviews," +
            "p.postdate," +
            "lastactivedate," +
            "p.votes,    " +
            "p.classroomid," +
            "q.ispublic as publicQuestion,  " +
            "p.approvalstatus" +
            " from question q    " +
            " join post p on p.postid = q.questionid" +
            " join appuser u on u.appuserId = p.postedby " +
            " where questionid = #{questionId}"})
    @Results({
            @Result(column = "questionId", property = "questionId"),
            @Result(column = "refsubjectid", property = "refSubjectId"),
            @Result(column = "questionlevelid", property= "questionLevelEnum", javaType = QuestionLevelEnum.class,
                    typeHandler = QuestionLevelEnumTypeHandler.class),
            @Result(column = "refquestionstatusid", property= "refQuestionStatusId", javaType = QuestionStatusEnum.class,
            typeHandler = QuestionStatusEnumTypeHandler.class),
            @Result(column = "postdate", property = "postDate", javaType = Date.class, typeHandler =
                    DateTimeTypeHandler.class),
            @Result(column = "lastactivedate", property = "lastActiveDate", javaType = Date.class,
                    typeHandler = DateTimeTypeHandler.class),



            @Result(column = "title", property= "title"),
            @Result(column = "noofviews", property= "noOfViews"),
            @Result(column = "ispublic", property= "publicQuestion"),
            @Result(column = "approvalstatus", property= "approvalStatus"),

            @Result(column = "postedby", property= "postedBy.appUserId"),
            @Result(column = "firstname", property= "postedBy.firstName"),
            @Result(column = "lastName", property= "postedBy.lastName"),
            @Result(column = "posttype", property= "postTypeEnum", javaType = PostTypeEnum.class,
                    typeHandler = PostTypeEnumTypeHandler.class),
            @Result(property="answers", column="questionid", javaType= List.class, many=@Many(select="getAnswers")),
            @Result(property="tags", column="questionid", javaType= List.class, many=@Many(select="getQuestionTags"))

    })
    Question getQuestionById(Integer questionId);


    @Select(" select tagid from questiontag where questionid = #{questionId}; ")
    List<Integer> getQuestionTags(Integer questionId);

//    https://stackoverflow.com/questions/33151873/one-to-many-relationship-in-mybatis
    @Select({"select questionid," +
            "refsubjectid," +
            "questionlevelid," +
            "p.postedby," +
            "p.posttype," +
            "u.firstname," +
            "u.middlename," +
            "u.lastName," +
            "refquestionstatusid," +
            "title," +
            "p.content," +
            "p.noofviews," +
            "p.postdate," +
            "lastactivedate," +
            "p.votes,    " +
            "p.classroomid," +
            "q.ispublic,  " +
            "p.approvalstatus" +
            " from question q    " +
            " join post p on p.postid = q.questionid" +
            " join appuser u on u.appuserId = p.postedby " +
            " where p.postedby = #{appUserId}" +
            " order by p.postdate desc limit #{page}, 5 "})
    // TODO: 27/01/18 remove hardcoded page size of 5 above
    @Results({
            @Result(column = "questionid", property = "questionId"),
            @Result(column = "refsubjectid", property = "refSubjectId"),
            @Result(column = "questionlevelid", property= "questionLevelEnum", javaType = QuestionLevelEnum.class,
                    typeHandler = QuestionLevelEnumTypeHandler.class),
            @Result(column = "refquestionstatusid", property= "refQuestionStatusId", javaType = QuestionStatusEnum.class,
                    typeHandler = QuestionStatusEnumTypeHandler.class),
            @Result(column = "postdate", property = "postDate", javaType = Date.class, typeHandler = DateTimeTypeHandler.class),
            @Result(column = "lastactivedate", property = "lastActiveDate", javaType = Date.class, typeHandler = DateTimeTypeHandler.class),

            @Result(column = "title", property= "title"),
            @Result(column = "noofviews", property= "noOfViews"),
            @Result(column = "ispublic", property= "publicQuestion"),
            @Result(column = "approvalstatus", property= "approvalStatus"),

            @Result(column = "postedby", property= "postedBy.appUserId"),
            @Result(column = "firstname", property= "postedBy.firstName"),
            @Result(column = "lastName", property= "postedBy.lastName"),
            @Result(column = "posttype", property= "postTypeEnum", javaType = PostTypeEnum.class,
                    typeHandler = PostTypeEnumTypeHandler.class),
            @Result(property="answers", column="questionid", javaType= List.class, many=@Many(select="getAnswers")),
            @Result(property="tags", column="questionid", javaType= List.class, many=@Many(select="getQuestionTags"))


    })
    List<Question> getUsersQuestions(@Param("appUserId")Integer appUserId, @Param("page")Integer page);


    @Select({"SELECT " +
            " a.answerid," +
            " a.questionid," +
            " p.votes," +
            " p.postedby," +
            " p.content," +
            " p.postdate," +
            " p.noofviews," +
            " p.posttype," +
            " p.classroomid," +
            " p.approvalstatus," +
            " u.firstname," +
            " u.middlename," +
            " u.lastName" +
            " From answer a" +
            " JOIN post p ON  a.answerid = p.postId" +
            " JOIN appuser u ON  p.postedby = u.appuserId" +
            " WHERE a.questionid = #{questionId}"})
    @Results({
            @Result(column = "answerId", property= "answerId"),
            @Result(column = "noofviews", property= "noOfViews"),
            @Result(column = "noofviews", property= "noOfViews"),
            @Result(column = "approvalstatus", property= "approvalStatus"),
            @Result(column = "postedby", property= "postedBy.appUserId"),
            @Result(column = "firstname", property= "postedBy.firstName"),
            @Result(column = "lastName", property= "postedBy.lastName"),
            @Result(column = "posttype", property= "postTypeEnum", javaType = PostTypeEnum.class,
                    typeHandler = PostTypeEnumTypeHandler.class),
            @Result(column = "postdate", property = "postDate", javaType = Date.class, typeHandler = DateTimeTypeHandler.class)

    })
    List<Answer> getAnswers(Integer questionId);


    @Insert("insert into favoritepost (userid, questionid) values (#{appUserId}, #{questionId})")
    void markAsFavorite(@Param("appUserId") Integer appUserId, @Param("questionId") Integer questionId);

    @Delete(" delete from favoritepost where userid = #{appUserId} and questionid = #{questionId} ")
    void removeFromFavorites(@Param("appUserId") Integer appUserId, @Param("questionId") Integer questionId);

    @Select("select q.questionid," +
            "refsubjectid," +
            "questionlevelid," +
            "p.postedby," +
            "p.posttype," +
            "u.firstname," +
            "u.middlename," +
            "u.lastName," +
            "refquestionstatusid," +
            "title," +
            "p.content," +
            "p.noofviews," +
            "p.postdate," +
            "lastactivedate," +
            "p.votes,    " +
            "p.classroomid," +
            "q.ispublic as publicQuestion,  " +
            "p.approvalstatus" +
            " from question q    " +
            " join post p on p.postid = q.questionid" +
            " join appuser u on u.appuserId = p.postedby " +
            " join favoritepost f on f.questionid = q.questionid" +
            " where f.userid = #{appUserId} " +
            " order by p.postdate desc limit #{page}, 5 "

    )

    @Results({
            @Result(column = "questionid", property = "questionId"),
            @Result(column = "refsubjectid", property = "refSubjectId"),
            @Result(column = "questionlevelid", property= "questionLevelEnum", javaType = QuestionLevelEnum.class,
                    typeHandler = QuestionLevelEnumTypeHandler.class),
            @Result(column = "refquestionstatusid", property= "refQuestionStatusId", javaType = QuestionStatusEnum.class,
                    typeHandler = QuestionStatusEnumTypeHandler.class),
            @Result(column = "postdate", property = "postDate", javaType = Date.class, typeHandler = DateTimeTypeHandler.class),
            @Result(column = "lastactivedate", property = "lastActiveDate", javaType = Date.class, typeHandler = DateTimeTypeHandler.class),



            @Result(column = "title", property= "title"),
            @Result(column = "noofviews", property= "noOfViews"),
            @Result(column = "ispublic", property= "publicQuestion"),
            @Result(column = "approvalstatus", property= "approvalStatus"),

            @Result(column = "postedby", property= "postedBy.appUserId"),
            @Result(column = "firstname", property= "postedBy.firstName"),
            @Result(column = "lastName", property= "postedBy.lastName"),
            @Result(column = "posttype", property= "postTypeEnum", javaType = PostTypeEnum.class,
                    typeHandler = PostTypeEnumTypeHandler.class),
            @Result(property="answers", column="questionid", javaType= List.class, many=@Many(select="getAnswers")),
            @Result(property="tags", column="questionid", javaType= List.class, many=@Many(select="getQuestionTags"))

    })
    List<Question> getFavoriteQuestions(@Param("appUserId") Integer appUserId, @Param("page") Integer page);

    @Select("SELECT count(Id) from favoritepost where userid = #{appUserId} and questionid = #{questionId}")
    boolean isFavorite( @Param("questionId") Integer questionId, @Param("appUserId") Integer appUserId);
}
