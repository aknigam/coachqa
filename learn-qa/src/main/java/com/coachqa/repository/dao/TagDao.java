package com.coachqa.repository.dao;

import com.coachqa.entity.Tag;

import java.util.List;


public interface TagDao {
    Tag addtag(Integer userId, final Tag tag) ;
    Tag getTagById(Integer tagId) ;

    Tag getTagByName(String tagName) ;
    List<Tag> findSimilarTags(String tag, Integer noOfResultsRequested) ;

}
