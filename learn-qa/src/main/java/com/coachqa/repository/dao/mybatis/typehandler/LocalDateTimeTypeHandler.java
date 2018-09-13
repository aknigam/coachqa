package com.coachqa.repository.dao.mybatis.typehandler;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.TimeZone;

/**
 * Created by a.nigam on 24/07/17.
 */
public class LocalDateTimeTypeHandler implements TypeHandler<LocalDateTime> {

    @Override
    public void setParameter(PreparedStatement ps, int i, LocalDateTime parameter, JdbcType jdbcType) throws SQLException {
        // https://stackoverflow.com/questions/23944370/how-to-get-milliseconds-from-localdatetime-in-java-8
        ps.setTimestamp(i, new Timestamp(parameter.atZone( ZoneId.systemDefault()).toInstant().toEpochMilli()));
    }

    @Override
    public LocalDateTime getResult(ResultSet rs, String columnName) throws SQLException {
        // https://stackoverflow.com/questions/44883432/long-timestamp-to-localdatetime/44883570
        Timestamp time = rs.getTimestamp(columnName);
        return LocalDateTime.ofInstant(Instant.ofEpochMilli(time.getTime()), TimeZone
                .getDefault().toZoneId());

    }

    @Override
    public LocalDateTime getResult(ResultSet rs, int columnIndex) throws SQLException {
        Timestamp voteDate = rs.getTimestamp(columnIndex);
        return LocalDateTime.ofInstant(Instant.ofEpochMilli(voteDate.getTime()), TimeZone
                .getDefault().toZoneId());
    }

    @Override
    public LocalDateTime getResult(CallableStatement cs, int columnIndex) throws SQLException {
        return null;
    }
}
