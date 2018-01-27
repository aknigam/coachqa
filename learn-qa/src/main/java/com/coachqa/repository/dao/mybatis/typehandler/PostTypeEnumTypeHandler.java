package com.coachqa.repository.dao.mybatis.typehandler;

import com.coachqa.enums.PostTypeEnum;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PostTypeEnumTypeHandler implements TypeHandler<PostTypeEnum> {
    @Override
    public void setParameter(PreparedStatement ps, int i, PostTypeEnum parameter, JdbcType jdbcType) throws SQLException {
        ps.setInt(i, parameter.getType());
    }

    @Override
    public PostTypeEnum getResult(ResultSet rs, String columnName) throws SQLException {

        int postTypeId = rs.getInt(columnName);
        // TODO: 20/01/18 implement fromId method in the enum
        return postTypeId == 1? PostTypeEnum.QUESTION : PostTypeEnum.ANSWER;
    }

    @Override
    public PostTypeEnum getResult(ResultSet rs, int columnIndex) throws SQLException {
        int postTypeId = rs.getInt(columnIndex);
        // TODO: 20/01/18 implement fromId method in the enum
        return postTypeId == 1? PostTypeEnum.QUESTION : PostTypeEnum.ANSWER;
    }

    @Override
    public PostTypeEnum getResult(CallableStatement cs, int columnIndex) throws SQLException {
        return null;
    }
}
