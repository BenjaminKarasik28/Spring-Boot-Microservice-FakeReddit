package com.example.postapi.service;

import com.example.postapi.model.Post;
import org.springframework.stereotype.Service;


public interface PostService {

    public Post createPost(Post post, String username);
}
