package com.coachqa.repository.dao.mybatis.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface AnswerMapper {

    @Insert("INSERT INTO answer (answerid, questionid) VALUE (#{answerId}, #{questionId} )")
    void addAnswer(@Param("answerId") int answerId, @Param("questionId") int questionId);
}
