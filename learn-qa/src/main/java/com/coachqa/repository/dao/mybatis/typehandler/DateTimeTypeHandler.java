package com.coachqa.repository.dao.mybatis.typehandler;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;
import org.joda.time.DateTime;

import java.sql.*;

/**
 * Created by a.nigam on 24/07/17.
 */
public class DateTimeTypeHandler implements TypeHandler<DateTime> {

    @Override
    public void setParameter(PreparedStatement ps, int i, DateTime parameter, JdbcType jdbcType) throws SQLException {
        ps.setTimestamp(i, new Timestamp(parameter.getMillis()));
    }

    @Override
    public DateTime getResult(ResultSet rs, String columnName) throws SQLException {

        Timestamp voteDate = rs.getTimestamp(columnName);
        return new DateTime(voteDate.getTime());
    }

    @Override
    public DateTime getResult(ResultSet rs, int columnIndex) throws SQLException {
        Timestamp voteDate = rs.getTimestamp(columnIndex);
        return new DateTime(voteDate.getTime());
    }

    @Override
    public DateTime getResult(CallableStatement cs, int columnIndex) throws SQLException {
        return null;
    }
}
