package com.coachqa.repository.dao;

import com.coachqa.entity.Tag;
import com.coachqa.repository.dao.impl.BaseDao;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;


public interface TagDao {
    Tag addtag(Integer userId, final Tag tag) ;
    Tag getTagById(Integer tagId) ;

    Tag getTagByName(String tagName) ;
    List<Tag> findSimilarTags(String tag, Integer noOfResultsRequested) ;

}
