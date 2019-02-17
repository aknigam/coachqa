package com.coachqa.entity;

import com.coachqa.enums.PostTypeEnum;

import java.util.Date;

public class PostApprovalRequest extends ApprovalRequest {

    private Post post;

    private PostTypeEnum postType;

    public PostApprovalRequest(){}

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public PostTypeEnum getPostType() {
        return postType;
    }

    public void setPostType(PostTypeEnum postType) {
        this.postType = postType;
    }
}
