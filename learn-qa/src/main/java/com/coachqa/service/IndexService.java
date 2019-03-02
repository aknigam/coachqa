package com.coachqa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


public interface IndexService {

    void updatePostExtractedtext(Integer postId, String imageExtractedText);
}
