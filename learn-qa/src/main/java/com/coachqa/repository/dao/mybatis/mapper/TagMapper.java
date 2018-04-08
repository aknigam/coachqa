package com.coachqa.repository.dao.mybatis.mapper;

import com.coachqa.entity.Question;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;


@Mapper
public interface TagMapper {

    @Delete("Delete from questiontag where questionid = #{questionId} ")
    void deleteTags(Integer questionId);

//    @Insert("Insert into questionTag (QuestionId, TagId) values (#{questionId}, #{tagId})")

    @Insert({
        "<script>",
                "Insert into questiontag (questionid, tagid) ",
                "VALUES" +
                        "<foreach item='tagId' collection='tagIds' open='' separator=',' close=''>" +
                        "( #{questionId} , #{tagId,jdbcType=INTEGER} )"+
                        "</foreach>",
                "</script>"})
    void addTags(@Param("questionId") int questionId, @Param("tagIds") List<Integer> tagIds);


    @Select("select qt.questionid, p.content from questiontag qt " +
            " join question q on q.questionid = qt.questionId " +
            " join post p on p.postId = q.questionid" +
            " join tag t on t.tagid = qt.tagid" +
            " WHERE t.tagid = #{tagId}" +
            " and p.approvalstatus = 0 ")
    @Results({

            @Result(column = "content", property = "content")
    })
    List<Question> getQuestionsByTag(int tagId);
}
