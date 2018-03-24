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

    @Update("Update question set RefSubjectId = #{refSubjectId} , Title =  #{title} ,  IsPublic = #{publicQuestion}  where questionId = #{questionId} ")
    void updateQuestion(Question updatedQuestion);

    @Insert("insert into question (questionId, RefSubjectId, QuestionlevelId, refQuestionStatusId , " +
            " lastactivedate, Title, isPublic) " +
            "values (#{postId},#{refSubjectId},1, #{statusId} ,  now(), #{title}, #{publicQuestion} )")
    void addQuestion(Question question);
    @Select({"select " +
            "questionId," +
            "RefSubjectId," +
            "QuestionLevelId," +
            "p.PostedBy," +
            "p.postType," +
            "u.Firstname," +
            "u.middleName," +
            "u.lastName," +
            "RefQuestionStatusId," +
            "Title," +
            "p.Content," +
            "p.NoOfViews," +
            "p.PostDate," +
            "LastActiveDate," +
            "p.Votes,    " +
            "p.ClassroomId," +
            "q.IsPublic as publicQuestion,  " +
            "p.ApprovalStatus" +
            " from Question q    " +
            " join post p on p.postid = q.questionid" +
            " join AppUser u on u.appuserId = p.postedby " +
            " where questionId = #{questionId}"})
    @Results({
            @Result(column = "questionId", property = "questionId"),
            @Result(column = "RefSubjectId", property = "refSubjectId"),
            @Result(column = "QuestionLevelId", property= "questionLevelEnum", javaType = QuestionLevelEnum.class,
                    typeHandler = QuestionLevelEnumTypeHandler.class),
            @Result(column = "RefQuestionStatusId", property= "refQuestionStatusId", javaType = QuestionStatusEnum.class,
            typeHandler = QuestionStatusEnumTypeHandler.class),
            @Result(column = "PostDate", property = "postDate", javaType = Date.class, typeHandler = DateTimeTypeHandler.class),
            @Result(column = "LastActiveDate", property = "lastActiveDate", javaType = Date.class, typeHandler = DateTimeTypeHandler.class),



            @Result(column = "Title", property= "title"),
            @Result(column = "NoOfViews", property= "noOfViews"),
            @Result(column = "IsPublic", property= "publicQuestion"),
            @Result(column = "ApprovalStatus", property= "approvalStatus"),

            @Result(column = "PostedBy", property= "postedBy.appUserId"),
            @Result(column = "Firstname", property= "postedBy.firstName"),
            @Result(column = "lastName", property= "postedBy.lastName"),
            @Result(column = "postType", property= "postTypeEnum", javaType = PostTypeEnum.class,
                    typeHandler = PostTypeEnumTypeHandler.class),
            @Result(property="answers", column="questionId", javaType= List.class, many=@Many(select="getAnswers")),
            @Result(property="tags", column="questionId", javaType= List.class, many=@Many(select="getQuestionTags"))

    })
    Question getQuestionById(Integer questionId);


    @Select(" select TagId from questionTag where QuestionId = #{questionId}; ")
    List<Integer> getQuestionTags(Integer questionId);

//    https://stackoverflow.com/questions/33151873/one-to-many-relationship-in-mybatis
    @Select({"select questionId," +
            "RefSubjectId," +
            "QuestionLevelId," +
            "p.PostedBy," +
            "p.postType," +
            "u.Firstname," +
            "u.middleName," +
            "u.lastName," +
            "RefQuestionStatusId," +
            "Title," +
            "p.Content," +
            "p.NoOfViews," +
            "p.PostDate," +
            "LastActiveDate," +
            "p.Votes,    " +
            "p.ClassroomId," +
            "q.IsPublic,  " +
            "p.ApprovalStatus" +
            " from Question q    " +
            " join post p on p.postid = q.questionid" +
            " join AppUser u on u.appuserId = p.postedby " +
            " where p.PostedBy = #{appUserId}" +
            " order by p.PostDate desc limit #{page}, 5 "})
    // TODO: 27/01/18 remove hardcoded page size of 5 above
    @Results({
            @Result(column = "questionId", property = "questionId"),
            @Result(column = "RefSubjectId", property = "refSubjectId"),
            @Result(column = "QuestionLevelId", property= "questionLevelEnum", javaType = QuestionLevelEnum.class,
                    typeHandler = QuestionLevelEnumTypeHandler.class),
            @Result(column = "RefQuestionStatusId", property= "refQuestionStatusId", javaType = QuestionStatusEnum.class,
                    typeHandler = QuestionStatusEnumTypeHandler.class),
            @Result(column = "PostDate", property = "postDate", javaType = Date.class, typeHandler = DateTimeTypeHandler.class),
            @Result(column = "LastActiveDate", property = "lastActiveDate", javaType = Date.class, typeHandler = DateTimeTypeHandler.class),

            @Result(column = "Title", property= "title"),
            @Result(column = "NoOfViews", property= "noOfViews"),
            @Result(column = "IsPublic", property= "publicQuestion"),
            @Result(column = "ApprovalStatus", property= "approvalStatus"),

            @Result(column = "PostedBy", property= "postedBy.appUserId"),
            @Result(column = "Firstname", property= "postedBy.firstName"),
            @Result(column = "lastName", property= "postedBy.lastName"),
            @Result(column = "postType", property= "postTypeEnum", javaType = PostTypeEnum.class,
                    typeHandler = PostTypeEnumTypeHandler.class),
            @Result(property="answers", column="questionId", javaType= List.class, many=@Many(select="getAnswers")),
            @Result(property="tags", column="questionId", javaType= List.class, many=@Many(select="getQuestionTags"))


    })
    List<Question> getUsersQuestions(@Param("appUserId")Integer appUserId, @Param("page")Integer page);


    @Select({"SELECT " +
            " a.AnswerId," +
            " a.questionId," +
            " p.Votes," +
            " p.PostedBy," +
            " p.Content," +
            " p.PostDate," +
            " p.NoOfViews," +
            " p.postType," +
            " p.ClassroomId," +
            " p.ApprovalStatus," +
            " u.Firstname," +
            " u.middleName," +
            " u.lastName" +
            " From answer a" +
            " JOIN post p ON  a.AnswerId = p.postId" +
            " JOIN AppUser u ON  p.postedby = u.appuserId" +
            " WHERE a.QuestionId = #{questionId}"})
    @Results({
            @Result(column = "answerId", property= "answerId"),
            @Result(column = "NoOfViews", property= "noOfViews"),
            @Result(column = "NoOfViews", property= "noOfViews"),
            @Result(column = "ApprovalStatus", property= "approvalStatus"),
            @Result(column = "PostedBy", property= "postedBy.appUserId"),
            @Result(column = "Firstname", property= "postedBy.firstName"),
            @Result(column = "lastName", property= "postedBy.lastName"),
            @Result(column = "postType", property= "postTypeEnum", javaType = PostTypeEnum.class,
                    typeHandler = PostTypeEnumTypeHandler.class),
            @Result(column = "PostDate", property = "postDate", javaType = Date.class, typeHandler = DateTimeTypeHandler.class)

    })
    List<Answer> getAnswers(Integer questionId);


    @Insert("insert into FavoritePost (UserId, QuestionId) values (#{appUserId}, #{questionId})")
    void markAsFavorite(@Param("appUserId") Integer appUserId, @Param("questionId") Integer questionId);

    @Delete(" delete from FavoritePost where UserId = #{appUserId} and QuestionId = #{questionId} ")
    void removeFromFavorites(@Param("appUserId") Integer appUserId, @Param("questionId") Integer questionId);

    @Select("select q.questionId," +
            "RefSubjectId," +
            "QuestionLevelId," +
            "p.PostedBy," +
            "p.postType," +
            "u.Firstname," +
            "u.middleName," +
            "u.lastName," +
            "RefQuestionStatusId," +
            "Title," +
            "p.Content," +
            "p.NoOfViews," +
            "p.PostDate," +
            "LastActiveDate," +
            "p.Votes,    " +
            "p.ClassroomId," +
            "q.IsPublic as publicQuestion,  " +
            "p.ApprovalStatus" +
            " from Question q    " +
            " join post p on p.postid = q.questionid" +
            " join AppUser u on u.appuserId = p.postedby " +
            " join FavoritePost f on f.QuestionId = q.questionId" +
            " where f.UserId = #{appUserId} " +
            " order by p.PostDate desc limit #{page}, 5 "

    )

    @Results({
            @Result(column = "questionId", property = "questionId"),
            @Result(column = "RefSubjectId", property = "refSubjectId"),
            @Result(column = "QuestionLevelId", property= "questionLevelEnum", javaType = QuestionLevelEnum.class,
                    typeHandler = QuestionLevelEnumTypeHandler.class),
            @Result(column = "RefQuestionStatusId", property= "refQuestionStatusId", javaType = QuestionStatusEnum.class,
                    typeHandler = QuestionStatusEnumTypeHandler.class),
            @Result(column = "PostDate", property = "postDate", javaType = Date.class, typeHandler = DateTimeTypeHandler.class),
            @Result(column = "LastActiveDate", property = "lastActiveDate", javaType = Date.class, typeHandler = DateTimeTypeHandler.class),



            @Result(column = "Title", property= "title"),
            @Result(column = "NoOfViews", property= "noOfViews"),
            @Result(column = "IsPublic", property= "publicQuestion"),
            @Result(column = "ApprovalStatus", property= "approvalStatus"),

            @Result(column = "PostedBy", property= "postedBy.appUserId"),
            @Result(column = "Firstname", property= "postedBy.firstName"),
            @Result(column = "lastName", property= "postedBy.lastName"),
            @Result(column = "postType", property= "postTypeEnum", javaType = PostTypeEnum.class,
                    typeHandler = PostTypeEnumTypeHandler.class),
            @Result(property="answers", column="questionId", javaType= List.class, many=@Many(select="getAnswers")),
            @Result(property="tags", column="questionId", javaType= List.class, many=@Many(select="getQuestionTags"))

    })
    List<Question> getFavoriteQuestions(@Param("appUserId") Integer appUserId, @Param("page") Integer page);

    @Select("SELECT count(Id) from FavoritePost where UserId = #{appUserId} and QuestionId = #{questionId}")
    boolean isFavorite( @Param("questionId") Integer questionId, @Param("appUserId") Integer appUserId);
}
