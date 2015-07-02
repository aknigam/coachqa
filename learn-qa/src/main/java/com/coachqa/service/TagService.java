package com.coachqa.service;

import com.coachqa.entity.Tag;
import com.coachqa.repository.dao.TagDao;
import com.coachqa.repository.dao.impl.DBTagDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Stackoverflow: SOF
 * 1. As soon as the user types letters of tag, the search tag request goes to backend and matching tags are shown to the user to choose from.
 * 2. In case of new tag a validate request goes to server as soon as user completes typing tag name by hitting space or tab etc. SOF sends validate request
 * when the user moves to next widget
 * 3. New tag is created as part of posting the question.
 *
 */
@Service
public class TagService {

    private Map<String, List<Tag>> tags = new HashMap<>();

    @Autowired
    private TagDao tagDao;

    /**
     * this operation should fetch the results from cache
     */
    public List<Tag> findSimilarTags(String tag, Integer noOfResultsRequested){

        return tagDao.findSimilarTags(tag, noOfResultsRequested);
    }

    public Integer addTag(Integer userId, Tag tag){
        Tag existingTag = tagDao.getTagByName(tag.getTagName());
        if(existingTag != null){
            throw new RuntimeException("Tag ["+ tag.getTagName()+"] already exists.");
        }
        Integer tagId = tagDao.addtag(userId, tag).getTagId();
        return tagId;
    }

    public Tag getTagById(Integer tagId){
        return tagDao.getTagById(tagId);
    }

    public Tag getTagByName(String tagName){
        return tagDao.getTagByName(tagName);
    }

    public void deleteTag(Integer userId, Integer tagId){

    }


}
