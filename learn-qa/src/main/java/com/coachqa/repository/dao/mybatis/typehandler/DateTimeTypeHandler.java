package com.coachqa.repository.dao.mybatis.typehandler;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;

/**
 * Created by a.nigam on 24/07/17.
 */
public class DateTimeTypeHandler implements TypeHandler<Date> {

    @Override
    public void setParameter(PreparedStatement ps, int i, Date parameter, JdbcType jdbcType) throws SQLException {
        // https://stackoverflow.com/questions/23944370/how-to-get-milliseconds-from-localdatetime-in-java-8
        ps.setTimestamp(i, new Timestamp(parameter.getTime()));
    }

    @Override
    public Date getResult(ResultSet rs, String columnName) throws SQLException {
        // https://stackoverflow.com/questions/44883432/long-timestamp-to-localdatetime/44883570
        return rs.getTimestamp(columnName);

    }

    @Override
    public Date getResult(ResultSet rs, int columnIndex) throws SQLException {
        return rs.getTimestamp(columnIndex);

    }

    @Override
    public Date getResult(CallableStatement cs, int columnIndex) throws SQLException {
        return null;
    }
}
