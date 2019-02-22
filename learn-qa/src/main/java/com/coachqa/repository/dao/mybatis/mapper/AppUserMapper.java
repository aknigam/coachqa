package com.coachqa.repository.dao.mybatis.mapper;

import com.coachqa.entity.AppUser;
import com.coachqa.entity.PostVote;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.mapstruct.Mapper;

@Mapper
public interface AppUserMapper {

    @Insert("insert into appuser(email,pasword,firstname,middlename,lastname, accountId) values " +
            "(#{email}, #{password}, #{firstName}, #{middleName}, #{plastname}, #{account.accountId} )")
    @Options(useGeneratedKeys=true, keyProperty="appUserId")
    int addUer(AppUser user);

}
