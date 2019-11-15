package com.example.postapi.model;

public class PostComment {

    public Iterable<DummyComment> getPostComment() {
        return postComment;
    }

    public void setPostComment(Iterable<DummyComment> postComment) {
        this.postComment = postComment;
    }

    private  Iterable<DummyComment> postComment;
}
