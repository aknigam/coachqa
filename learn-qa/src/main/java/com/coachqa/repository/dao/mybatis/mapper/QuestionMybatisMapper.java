package com.coachqa.repository.dao.mybatis.mapper;

import com.coachqa.entity.Question;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;



@Mapper
public interface QuestionMybatisMapper {

    @Update("Update question set RefSubjectId = #{refSubjectId} , Title =  #{title} ,  IsPublic = #{publicQuestion}  where questionId = #{questionId} ")
    void updateQuestion(Question updatedQuestion);

    @Insert("insert into question (questionId, RefSubjectId, QuestionlevelId, refQuestionStatusId , " +
            " lastactivedate, Title, isPublic) " +
            "values (#{postId},#{refSubjectId},1, #{statusId} ,  now(), #{title}, #{publicQuestion} )")
    void addQuestion(Question question);
}
