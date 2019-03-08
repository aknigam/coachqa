package com.coachqa.service;

import com.coachqa.entity.AppUser;
import com.coachqa.entity.Question;

import java.util.List;


public interface IndexSearchService {

    void updatePostExtractedtext(Integer postId, String imageExtractedText);

    List<Integer> searchQuestions(Question criteria, int page, AppUser user);
}
