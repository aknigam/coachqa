package com.coachqa;

import java.util.List;

/**
 * This class represents a bookmak created by a user.
 */
public class UserBookmark {


    private List<Integer> tagIds;

    private String url;

    public UserBookmark(String url) {
        this.url = url;
    }

    public List<Integer> getTagIds() {
        return tagIds;
    }

    public String getUrl() {
        return url;
    }
}
