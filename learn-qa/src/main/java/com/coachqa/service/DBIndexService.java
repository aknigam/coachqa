package com.coachqa.service;

import com.coachqa.entity.AppUser;
import com.coachqa.entity.Question;
import com.coachqa.repository.dao.QuestionDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
public class DBIndexService implements IndexSearchService {

    private static final int NO_OF_PAGINATED_RESULTS = 5;

    @Autowired
    private QuestionDAO questionDao;

    @Autowired
    private PostService postService;

    public void updatePostExtractedtext(Integer postId, String imageExtractedText) {
        postService.updatePostExtractedtext(postId, imageExtractedText);
    }

    @Override
    public List<Integer> searchQuestions(Question criteria, int page, AppUser user) {
        return Collections.emptyList();
    }
}
