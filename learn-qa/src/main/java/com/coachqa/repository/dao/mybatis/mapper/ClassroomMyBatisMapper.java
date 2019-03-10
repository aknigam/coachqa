package com.coachqa.repository.dao.mybatis.mapper;

import com.coachqa.entity.Classroom;
import com.coachqa.entity.RefSubject;
import com.coachqa.repository.dao.mybatis.typehandler.ClassroomStatusTypeHandler;
import com.coachqa.ws.model.ClassroomMembership;
import org.apache.ibatis.annotations.Lang;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.mapstruct.Mapper;
import org.mybatis.scripting.freemarker.FreeMarkerLanguageDriver;

import java.util.List;


@Mapper
public interface ClassroomMyBatisMapper {

    @Select("select cm.classroomid, c.classname from classroommember cm join " +
            " classroom c on c.classroomid = cm.classroomid " +
            " where cm.appuserid = #{userId} and cm.status = 2" )
    List<Classroom> getUserClassrooms(Integer userId);

    @Select("select classroommemberid from classroommember where classroomid = #{param1} and appuserid = #{param2}")
    Integer getMembership(Integer classroomId, Integer user);

    // https://stackoverflow.com/questions/9185452/how-to-apply-a-method-to-a-parameter-in-mybatis
    // https://stackoverflow.com/questions/8912510/how-does-the-mybatis-parameter-replacement-work-in-selectprovider


//    @SelectProvider(type=ClassSqlProvider.class, method="getClassroomSql")
    @Select("select classroomid,minreputationtojoinid,classowner ,u.firstname,u.middlename,u.lastName," +
            " classname,ispublic, subjectId , c.accountId from classroom c" +
            " join appuser u ON u.appuserId = classowner where c.classroomid = #{classroomId}")
    @Results({
            @Result(column = "classroomid", property = "classroomId"),
            @Result(column = "minreputationtojoinid", property = "minReputationToJoinId"),
            @Result(column = "classowner", property = "classOwner.appUserId"),
            @Result(column = "firstname", property = "classOwner.firstName"),
            @Result(column = "middlename", property = "classOwner.middleName"),
            @Result(column = "lastName", property = "classOwner.lastName"),

            @Result(column = "classname", property = "className"),
            @Result(column = "ispublic", property = "isPublic"),
            @Result(column = "accountId", property = "account.accountId"),
            @Result(column = "subjectId", property = "subject.refSubjectId")
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
            @Result(column = "subjectname", property = "subject.subjectName"),
            @Result(column = "loggedInUserStatus", property = "loggedInUserStatus", typeHandler =
                    ClassroomStatusTypeHandler.class)
    })
    List<Classroom> searchClassrooms(@Param("page") Integer page,
                                     @Param("loginuserid") int loginUserId,
                                     @Param("myclassonly") boolean myClassesOnly,
                                     @Param("accountId") int accountId);

    @Select("SELECT" +
            "  classroommemberid as membershipId," +
            "  appuserid as memberId," +
            "  classroomid as classroomId," +
            "  status  as membershipStatus," +
            "  membershipstartdate  as startDate," +
            "  membershipexpirartiondate as expirationDate," +
            "  membershiprequestdate  as membershipRequestDate," +
            "  comments as requestComments " +
            "FROM classroommember cm " +
            "WHERE classroommemberid = #{membershipId}")
    @Results({
            @Result(column = "membershipStatus", property = "membershipStatus", typeHandler =
                    ClassroomStatusTypeHandler.class)
    })
    ClassroomMembership getMembershipDetails(Integer membershipId);


    @Update("UPDATE classroommember set status = #{status} where classroommemberid = #{membershipId}")
    void changeMembershipStatus( @Param("membershipId") Integer membershipId, @Param("status") Integer status);

    @Lang(FreeMarkerLanguageDriver.class)
    @Select("classroomMemberships.ftlh")
    @Results({
            @Result(column = "membershipStatus", property = "membershipStatus", typeHandler =
                    ClassroomStatusTypeHandler.class),

            @Result(column = "classname", property= "classroom.className"),
            @Result(column = "classroomId", property= "classroom.classroomId"),
            @Result(column = "firstname", property= "member.firstName"),
            @Result(column = "lastName", property= "member.lastName"),
            @Result(column = "middlename", property= "member.middleName"),
            @Result(column = "email", property= "member.email"),
            @Result(column = "memberId", property= "member.appUserId")
    })
    List<ClassroomMembership> getMembershipRequests(@Param("classroomid") Integer classroomId, @Param("ownerid")
            Integer appUserId, @Param("status") int status, @Param("page") Integer page);

    @Select("select cm.appuserid from classroommember cm where cm.status =  2 and cm.classroomid = #{classroomId};")
    List<Integer> getMembersList(@Param("classroomId") Integer classroomId);

    @Select("select p.postedby " +
            "from crajee.answer a " +
            "JOIN question q on q.questionid = a.questionid " +
            "RIGHT JOIN answer oa on oa.questionid = a.questionid " +
            "RIGHT JOIN crajee.post p on p.postid = oa.answerid " +
            "where a.answerid = #{postId};")
    List<Integer> getAllContributors(Integer postId);

    @Select("SELECT cm.appuserid as appuserId, cm.classroomid as classroomId " +
            "from classroommember cm " +
            "  JOIN classroom c on c.classroomid = cm.classroomid and c.classowner = #{approverId} " +
            "where status = 1")
    List<ClassroomMembership> getPendingMembershipRequests(Integer approverId);
}
