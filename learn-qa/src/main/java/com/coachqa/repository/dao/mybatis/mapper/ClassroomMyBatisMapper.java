package com.coachqa.repository.dao.mybatis.mapper;

import com.coachqa.entity.Classroom;
import org.apache.ibatis.annotations.Select;
import org.mapstruct.Mapper;

import java.util.List;


@Mapper
public interface ClassroomMyBatisMapper {

    @Select("select cm.ClassroomId, c.ClassName from classroommember cm join " +
            " classroom c on c.ClassroomId = cm.ClassroomId " +
            " where cm.AppUserId = #{userId}")
    List<Classroom> getUserClassrooms(Integer userId);

    @Select("select ClassroomMemberId from classroommember where ClassroomId = #{param1} and AppUserId = #{param2}")
    Integer getMembership(Integer classroomId, Integer user);
}
