package com.coachqa.repository.dao.mybatis.mapper;

import com.coachqa.entity.AppUser;
import com.coachqa.entity.PostVote;
import com.coachqa.entity.UserTypeEnum;
import com.coachqa.enums.PostTypeEnum;
import com.coachqa.enums.QuestionLevelEnum;
import com.coachqa.enums.QuestionStatusEnum;
import com.coachqa.repository.dao.mybatis.typehandler.DateTimeTypeHandler;
import com.coachqa.repository.dao.mybatis.typehandler.PostTypeEnumTypeHandler;
import com.coachqa.repository.dao.mybatis.typehandler.QuestionLevelEnumTypeHandler;
import com.coachqa.repository.dao.mybatis.typehandler.QuestionStatusEnumTypeHandler;
import com.coachqa.repository.dao.mybatis.typehandler.UserTypeEnumHandler;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.mapstruct.Mapper;

import java.util.Date;
import java.util.List;

@Mapper
public interface AppUserMapper {

    // TODO: 06/03/19 userType should also be set here
    @Insert("insert into appuser(email,pasword,firstname,middlename,lastname, accountId, usertypeid) values " +
            "(#{email}, #{password}, #{firstName}, #{middleName}, #{lastName}, #{account.accountId} , #{userType.id} )")
    @Options(useGeneratedKeys=true, keyProperty="appUserId")
    int addUer(AppUser user);

    @Select("select appuserid, firstname, email, lastname, u.accountId, usertypeid , a.accountName " +
            " from appuser u join account a on a.accountId = u.accountId" +
            " where email = #{userEmail}")
    @Results({
            @Result(column = "usertypeid", property= "userType", javaType = UserTypeEnum.class,
                    typeHandler = UserTypeEnumHandler.class),
            @Result(column = "appuserid", property= "appUserId"),
            @Result(column = "accountId", property= "account.accountId"),
            @Result(column = "accountName", property= "account.accountName"),
            @Result(property="androidTokens", column="appuserid", javaType= List.class, many=@Many
                    (select="getAndroidTokens"))
    })
    AppUser getUserByEmail(String userEmail);

    @Update("Update appuser set android_token =  #{androidToken} where appuserid = #{appUserId} and android_token is null")
    void addAndroidToken(@Param("androidToken") String androidToken, @Param("appUserId") Integer appUserId);

    @Select("select appuserid, firstname, email, lastname, u.accountId, usertypeid , a.accountName " +
            " from appuser u join account a on a.accountId = u.accountId" +
            " where appUserId = #{userId}")
    @Results({
            @Result(column = "usertypeid", property= "userType", javaType = UserTypeEnum.class,
                    typeHandler = UserTypeEnumHandler.class),
            @Result(column = "appuserid", property= "appUserId"),
            @Result(column = "accountId", property= "account.accountId"),
            @Result(column = "accountName", property= "account.accountName"),
            @Result(property="androidTokens", column="appuserid", javaType= List.class, many=@Many
                    (select="getAndroidTokens"))
    })
    AppUser getUserById(@Param("userId") Integer userId);

    @Select("SELECT  androidtoken from appuserdetail where appuserid = #{appuserid}")
    List<String> getAndroidTokens(@Param("appuserid") int appuserid );
}
