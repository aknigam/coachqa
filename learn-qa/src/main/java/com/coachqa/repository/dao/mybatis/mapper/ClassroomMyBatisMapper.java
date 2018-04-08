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

    @Select("select cm.classroomid, c.classname from classroommember cm join " +
            " classroom c on c.classroomid = cm.classroomid " +
            " where cm.appuserid = #{userId}")
    List<Classroom> getUserClassrooms(Integer userId);

    @Select("select classroommemberid from classroommember where classroomid = #{param1} and appuserid = #{param2}")
    Integer getMembership(Integer classroomId, Integer user);

    @Select("select classroomid,minreputationtojoinid,classowner ,u.firstname,u.middlename,u.lastName," +
            " classname,ispublic from classroom c" +
            " join appuser u ON u.appuserId = classowner where c.classroomid = #{classroomId}")
    @Results({
            @Result(column = "classroomid", property = "classroomId"),
            @Result(column = "minreputationtojoinid", property = "minReputationToJoinId"),
            @Result(column = "classowner", property= "classOwner.appUserId"),
            @Result(column = "firstname", property= "classOwner.firstName"),
            @Result(column = "middlename", property = "classOwner.middleName"),
            @Result(column = "lastName", property = "classOwner.lastName"),

            @Result(column = "classname", property= "className"),
            @Result(column = "ispublic", property= "isPublic")

    })
    Classroom getClassroomByIdentifier(Integer classroomId);
}
