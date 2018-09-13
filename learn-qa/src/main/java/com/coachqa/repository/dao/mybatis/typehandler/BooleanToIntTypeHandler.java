package com.coachqa.repository.dao.mybatis.typehandler;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BooleanToIntTypeHandler implements TypeHandler<Boolean>{
    @Override
    public void setParameter(PreparedStatement ps, int i, Boolean parameter, JdbcType jdbcType) throws SQLException {

    }

    @Override
    public Boolean getResult(ResultSet rs, String columnName) throws SQLException {
        return null;
    }

    @Override
    public Boolean getResult(ResultSet rs, int columnIndex) throws SQLException {
        return null;
    }

    @Override
    public Boolean getResult(CallableStatement cs, int columnIndex) throws SQLException {
        return null;
    }
}
