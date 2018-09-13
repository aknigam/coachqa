package com.coachqa.repository.dao.mybatis.mapper;

import com.coachqa.entity.Classroom;
import com.coachqa.entity.RefSubject;
import com.coachqa.enums.PostTypeEnum;
import com.coachqa.enums.QuestionLevelEnum;
import com.coachqa.enums.QuestionStatusEnum;
import com.coachqa.repository.dao.mybatis.typehandler.DateTimeTypeHandler;
import com.coachqa.repository.dao.mybatis.typehandler.PostTypeEnumTypeHandler;
import com.coachqa.repository.dao.mybatis.typehandler.QuestionLevelEnumTypeHandler;
import com.coachqa.repository.dao.mybatis.typehandler.QuestionStatusEnumTypeHandler;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Lang;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.mapstruct.Mapper;
import org.mybatis.scripting.freemarker.FreeMarkerLanguageDriver;

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
            @Result(column = "ispublic", property= "isPublic"),
            @Result(property="subjects", column="classroomid", javaType= List.class, many=@Many
                    (select="getClassroomSubjects"))

    })
    Classroom getClassroomByIdentifier(Integer classroomId);

    @Select("select s.refsubjectid as refSubjectId, s.subjectname as subjectName, s.subjectdescription as " +
            "subjectDescription  from refsubject s " +
            "JOIN classroomsubject cs on cs.subjectId = s.refsubjectid " +
            "where cs.classroomId = #{classroomId}")
    List<RefSubject> getClassroomSubjects(Integer classroomId);

    @Lang(FreeMarkerLanguageDriver.class)
    @Select("classrooms.ftlh")
//    @Select("SELECT" +
//            "  classroomid as classroomId, " +
//            "  classname as className, " +
//            "  classowner, " +
//            "  u.firstname, " +
//            "  u.middlename, " +
//            "  u.lastname, " +
//            "  description, " +
//            "  subjectId, " +
//            "  s.subjectname " +
//            "FROM classroom c " +
//            "  JOIN appuser u ON u.appuserid = c.classowner " +
//            "  JOIN refsubject s ON s.refsubjectid = c.subjectId " +
//            " <script> " +
//            "   " +
//            " </script> " +
//            "ORDER BY classroomid " +
//            "LIMIT #{page}, 10")
    @Results({
            @Result(column = "classowner", property= "classOwner.appUserId"),
            @Result(column = "firstname", property= "classOwner.firstName"),
            @Result(column = "middlename", property = "classOwner.middleName"),
            @Result(column = "lastName", property = "classOwner.lastName"),
            @Result(column = "subjectId", property = "subject.refSubjectId"),
            @Result(column = "subjectname", property = "subject.subjectName")
    })
    List<Classroom> searchClassrooms(@Param("page") Integer page, @Param("loginuserid") int loginUserId, @Param
            ("myclassonly") boolean myClassesOnly);

}
