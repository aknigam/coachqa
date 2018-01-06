package com.coachqa.repository.dao.impl;

import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@Repository
public class FileUploadDao extends BaseDao {

    private static String fileInsertQuery = "INSERT INTO PostMedia (ImageContent) VALUES (?)";

    public int persist(byte[] bytes) throws SQLException {

        jdbcTemplate = getJdbcTemplate();
        final PreparedStatement[] ps = {null};
        try {
            KeyHolder holder = new GeneratedKeyHolder();
            jdbcTemplate.update(new PreparedStatementCreator() {
                @Override
                public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                    ps[0] = connection.prepareStatement(fileInsertQuery, Statement.RETURN_GENERATED_KEYS);
                    ps[0].setBytes(1, bytes);
                    return ps[0];

                }
            }, holder);


            return holder.getKey().intValue();
        }
        finally {
            ps[0].close();
        }

    }

    private static String imageReadQuery = "Select ImageContent from PostMedia where id = ?";

    public byte[] readImage(Integer imageId) {

        return jdbcTemplate.queryForObject(imageReadQuery, new Integer[]{imageId}, new RowMapper<byte[]>() {
            @Override
            public byte[] mapRow(ResultSet rs, int rowNum) throws SQLException {
                return rs.getBytes("ImageContent");
            }
        });

    }
}
