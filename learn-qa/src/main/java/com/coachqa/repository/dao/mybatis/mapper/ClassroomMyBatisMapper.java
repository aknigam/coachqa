package com.coachqa.repository.dao.mybatis.mapper;

import com.coachqa.entity.Classroom;
import com.coachqa.enums.PostTypeEnum;
import com.coachqa.enums.QuestionLevelEnum;
import com.coachqa.enums.QuestionStatusEnum;
import com.coachqa.repository.dao.mybatis.typehandler.DateTimeTypeHandler;
import com.coachqa.repository.dao.mybatis.typehandler.PostTypeEnumTypeHandler;
import com.coachqa.repository.dao.mybatis.typehandler.QuestionLevelEnumTypeHandler;
import com.coachqa.repository.dao.mybatis.typehandler.QuestionStatusEnumTypeHandler;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.mapstruct.Mapper;

import java.util.Date;
import java.util.List;


@Mapper
public interface ClassroomMyBatisMapper {

    @Select("select cm.ClassroomId, c.ClassName from classroommember cm join " +
            " classroom c on c.ClassroomId = cm.ClassroomId " +
            " where cm.AppUserId = #{userId}")
    List<Classroom> getUserClassrooms(Integer userId);

    @Select("select ClassroomMemberId from classroommember where ClassroomId = #{param1} and AppUserId = #{param2}")
    Integer getMembership(Integer classroomId, Integer user);

    @Select("select ClassroomId,MinReputationToJoinId,ClassOwner ,u.Firstname,u.middleName,u.lastName," +
            " ClassName,IsPublic from classroom c" +
            " joinAppUser u ON u.appuserId = classownerwherec.classroomId = #{classroomId}")
    @Results({
            @Result(column = "ClassroomId", property = "classroomId"),
            @Result(column = "MinReputationToJoinId", property = "minReputationToJoinId"),
            @Result(column = "ClassOwner", property= "classOwner.appUserId"),
            @Result(column = "Firstname", property= "classOwner.firstName"),
            @Result(column = "middleName", property = "classOwner.middleName"),
            @Result(column = "lastName", property = "classOwner.lastName"),

            @Result(column = "ClassName", property= "className"),
            @Result(column = "IsPublic", property= "isPublic")

    })
    Classroom getClassroomByIdentifier(Integer classroomId);
}
