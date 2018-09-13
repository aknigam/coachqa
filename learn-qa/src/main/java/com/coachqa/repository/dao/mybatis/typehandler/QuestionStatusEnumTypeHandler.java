package com.coachqa.repository.dao.mybatis.typehandler;

import com.coachqa.enums.QuestionStatusEnum;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by a.nigam on 24/07/17.
 */
public class QuestionStatusEnumTypeHandler implements TypeHandler<QuestionStatusEnum> {

    @Override
    public void setParameter(PreparedStatement ps, int i, QuestionStatusEnum parameter, JdbcType jdbcType) throws SQLException {
        ps.setInt(i, parameter.getId());
    }

    @Override
    public QuestionStatusEnum getResult(ResultSet rs, String columnName) throws SQLException {

        int statusId = rs.getInt(columnName);
        // TODO: 20/01/18 implement fromId method in the enum
        return QuestionStatusEnum.NEW;
    }

    @Override
    public QuestionStatusEnum getResult(ResultSet rs, int columnIndex) throws SQLException {
        int statusId = rs.getInt(columnIndex);
        // TODO: 20/01/18 implement fromId method in the enum
        return QuestionStatusEnum.NEW;
    }

    @Override
    public QuestionStatusEnum getResult(CallableStatement cs, int columnIndex) throws SQLException {
        return null;
    }
}
