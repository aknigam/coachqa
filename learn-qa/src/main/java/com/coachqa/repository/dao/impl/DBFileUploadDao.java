package com.coachqa.repository.dao.impl;

import com.coachqa.repository.dao.FileUploadDao;
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

@Repository("DBFileUploadDao")
public class DBFileUploadDao extends BaseDao implements FileUploadDao {

    private static String fileInsertQuery = "INSERT INTO postmedia (imagecontent) VALUES (?)";

    private static String imageReadQuery = "Select imagecontent from postmedia where id = ?";

    public String persist(byte[] bytes, int accountId) {
        // TODO: 18/02/19 use the accountId to segregate this accounts files
        // TODO: 18/02/19 accountId is part of postMedia
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


            return "l-"+String.valueOf( holder.getKey().intValue()) ;
        }
        finally {
            try {
                ps[0].close();
            } catch (SQLException e) {
                throw new RuntimeException("Unexpected error occurred while saving file. Please try again");
            }
        }

    }



    public byte[] readImage(Integer imageId) {

        return jdbcTemplate.queryForObject(imageReadQuery, new Integer[]{imageId}, new RowMapper<byte[]>() {
            @Override
            public byte[] mapRow(ResultSet rs, int rowNum) throws SQLException {
                return rs.getBytes("imagecontent");
            }
        });

    }
}
