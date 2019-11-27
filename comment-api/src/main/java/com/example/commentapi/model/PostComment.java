package com.example.commentapi.model;

import java.util.List;

public class PostComment {

    public Iterable<Comment> getPostComment() {
        return postComment;
    }

    public void setPostComment(Iterable<Comment> postComment) {
        this.postComment = postComment;
    }

    private  Iterable<Comment> postComment;



}
