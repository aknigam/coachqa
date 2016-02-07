package com.coachqa.repository.dao.impl;

import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class FileUploadDao extends BaseDao {

    private static String fileInsertQuery = "INSERT INTO POST_MEDIA (MEDIA_CONTENT) VALUES (?)";

    public int persist(byte[] bytes) {

        jdbcTemplate = getJdbcTemplate();
        KeyHolder holder = new GeneratedKeyHolder();
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement(fileInsertQuery, Statement.RETURN_GENERATED_KEYS);
                ps.setBytes(1, bytes);
                return ps;
            }
        }, holder);


        return holder.getKey().intValue();

    }
}
