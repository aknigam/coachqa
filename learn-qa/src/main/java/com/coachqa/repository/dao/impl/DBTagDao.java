package com.coachqa.repository.dao.impl;

import com.coachqa.entity.Tag;
import com.coachqa.repository.dao.TagDao;
import com.coachqa.repository.dao.impl.BaseDao;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class DBTagDao extends BaseDao implements TagDao{

    private AtomicInteger idCounter = new AtomicInteger(0);
    private Map<Integer, Tag> tags = new HashMap<>();
    private Map<String, Tag> tagNameMap = new HashMap<>();

    private static String m_addTagQuery = "INSERT INTO Tag (TagName,TagDescription) VALUES (?,?);";

    private static String tagGetByIdQuery = "Select * from Tag where tagId = ?";

    private static String tagGetByNameQuery = "Select * from Tag where TagName = ?";

    private static String similarTagQuery = "Select * from Tag where TagName like ?";


    @Override
    public Tag addtag(Integer userId, final Tag tag) {
        Object[] args = new Object[]{tag.getTagName(), tag.getTagDescription()};
        KeyHolder holder = new GeneratedKeyHolder();
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement(m_addTagQuery, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, tag.getTagName());
                ps.setString(2, tag.getTagDescription());
                return ps;
            }
        }, holder);

        Integer id = holder.getKey().intValue();
        tag.setTagId(id);
        return tag;
    }


    @Override
    public Tag getTagById(Integer tagId) {

        List<Tag> tags = jdbcTemplate.query(tagGetByIdQuery, new Integer[]{tagId}, new RowMapper<Tag>() {
            @Override
            public Tag mapRow(ResultSet rs, int i) throws SQLException {
                return new Tag(rs.getInt(1), rs.getString(2), rs.getString(3), 0);
            }
        });

        return tags.get(0);
    }

    /**
     * Cache is configured in LearnQAWebConfig
     */
    @Override
    @Cacheable(value="tags", key="#tagName")
    public Tag getTagByName(String tagName) {
        List<Tag> tags = jdbcTemplate.query(tagGetByNameQuery, new String[]{tagName}, new RowMapper<Tag>() {
            @Override
            public Tag mapRow(ResultSet rs, int i) throws SQLException {
                return new Tag(rs.getInt(1), rs.getString(2), rs.getString(3), 0);
            }
        });

        return tags.get(0);
    }

    @Override
    public List<Tag> findSimilarTags(String tag, Integer noOfResultsRequested) {

        String likeTag =  tag+"%";
        List<Tag> tags = jdbcTemplate.query(similarTagQuery, new String[]{likeTag}, new RowMapper<Tag>() {
            @Override
            public Tag mapRow(ResultSet rs, int i) throws SQLException {
                return new Tag(rs.getInt(1), rs.getString(2), rs.getString(3), 0);
            }
        });

        return tags;
    }
}
