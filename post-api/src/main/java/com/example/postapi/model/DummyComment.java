package com.example.postapi.model;

import java.util.List;

public class DummyComment {

    public DummyComment() {
    }

    private String text;
    private List<DummyComment> commentsByUser;
    private Long postId;
    private String username;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<DummyComment> getCommentsByUser() {
        return commentsByUser;
    }

    public void setCommentsByUser(List<DummyComment> commentsByUser) {
        this.commentsByUser = commentsByUser;
    }

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
