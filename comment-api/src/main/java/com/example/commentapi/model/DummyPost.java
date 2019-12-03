package com.example.commentapi.model;

public class DummyPost {

    private String title;
    private String description;
    private String username;
    private Long id;


    public DummyPost() {}

    public DummyPost(String title, String description, String username, Long id) {
        this.title = title;
        this.description = description;
        this.username = username;
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
