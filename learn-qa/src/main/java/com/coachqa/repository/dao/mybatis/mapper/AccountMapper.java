package com.coachqa.repository.dao.mybatis.mapper;

import com.coachqa.entity.Account;
import com.coachqa.entity.AccountPreference;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface AccountMapper {

    @Select(" Select accountId, accountName, accountDescription " +
            "from account where accountName = #{accountName}")
//    @Results({
//            @Result(property="accountPreferences", column="accountId", javaType= List.class, many=@Many(select=
//                    "getAccountPreferences"))
//    })
    public Account getByName( @Param( "accountName") String accountName);

    @Select(" Select accountId, accountName, accountDescription " +
            "from account where accountId = #{accountId}")
    @Results({
            @Result(property="accountPreferences", column="accountId", javaType= List.class, many=@Many(select=
                    "getAccountPreferences"))
    })
    Account fetchCompleteAccountDetails( @Param("accountId") int accountId);

    @Select(" SELECT postsNeedApproval from account_preference where accountId = #{accountId}")
    AccountPreference getAccountPreferences(int accountId);

    @Insert("Insert into account (accountName, accountDescription) value (#{accountName}, #{accountDescription}) ")
    @Options(useGeneratedKeys=true, keyProperty="accountId")
    int createAccount(Account account);
}
