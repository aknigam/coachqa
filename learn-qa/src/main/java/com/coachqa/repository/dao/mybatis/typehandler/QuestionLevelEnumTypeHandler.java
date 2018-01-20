package com.coachqa.repository.dao.mybatis.typehandler;

import com.coachqa.enums.QuestionLevelEnum;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class QuestionLevelEnumTypeHandler implements TypeHandler<QuestionLevelEnum> {

    @Override
    public void setParameter(PreparedStatement ps, int i, QuestionLevelEnum parameter, JdbcType jdbcType) throws SQLException {
        ps.setInt(i, parameter.getId());
    }

    @Override
    public QuestionLevelEnum getResult(ResultSet rs, String columnName) throws SQLException {

        int postTypeId = rs.getInt(columnName);
        // TODO: 20/01/18 implement fromId method in the enum
        return postTypeId == 1? QuestionLevelEnum.MEDIUM : QuestionLevelEnum.EASY;
    }

    @Override
    public QuestionLevelEnum getResult(ResultSet rs, int columnIndex) throws SQLException {
        int postTypeId = rs.getInt(columnIndex);
        // TODO: 20/01/18 implement fromId method in the enum
        return postTypeId == 1? QuestionLevelEnum.MEDIUM : QuestionLevelEnum.EASY;
    }

    @Override
    public QuestionLevelEnum getResult(CallableStatement cs, int columnIndex) throws SQLException {
        return null;
    }
}
