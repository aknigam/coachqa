package com.coachqa.repository.dao.mybatis.typehandler;

import com.coachqa.entity.UserTypeEnum;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserTypeEnumHandler implements TypeHandler<UserTypeEnum> {

    @Override
    public void setParameter(PreparedStatement ps, int i, UserTypeEnum parameter, JdbcType jdbcType) throws SQLException {
        ps.setInt(i, parameter.getId());
    }

    @Override
    public UserTypeEnum getResult(ResultSet rs, String columnName) throws SQLException {

        return UserTypeEnum.from(rs.getInt(columnName));
    }

    @Override
    public UserTypeEnum getResult(ResultSet rs, int columnIndex) throws SQLException {
        return UserTypeEnum.from(rs.getInt(columnIndex));
    }

    @Override
    public UserTypeEnum getResult(CallableStatement cs, int columnIndex) throws SQLException {
        return null;
    }
}
