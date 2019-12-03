package com.example.postapi.model;



public class DummyComment {

    public DummyComment() {
    }

    public DummyComment(String text, Long id, Long postId, String username) {
        this.text = text;
        this.id = id;
        this.postId = postId;
        this.username = username;
    }
    private String text;
    private Long id;
    private Long postId;
    private String username;

    public String getText() {
        return text;
    }
    public void setText(String text) {
        this.text = text;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
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
