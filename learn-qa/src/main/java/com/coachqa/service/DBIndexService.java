package com.coachqa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DBIndexService implements IndexService {

    @Autowired
    private PostService postService;

    public void updatePostExtractedtext(Integer postId, String imageExtractedText) {
        postService.updatePostExtractedtext(postId, imageExtractedText);
    }
}
