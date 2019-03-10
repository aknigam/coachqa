package com.coachqa.repository.dao.mybatis.mapper;

import com.coachqa.entity.AppUser;
import com.coachqa.entity.PostVote;
import com.coachqa.entity.UserTypeEnum;
import com.coachqa.enums.PostTypeEnum;
import com.coachqa.repository.dao.mybatis.typehandler.PostTypeEnumTypeHandler;
import com.coachqa.repository.dao.mybatis.typehandler.UserTypeEnumHandler;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.mapstruct.Mapper;

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
            @Result(column = "accountId", property= "account.accountId"),
            @Result(column = "accountName", property= "account.accountName")
    })
    AppUser getUserByEmail(String userEmail);
}
