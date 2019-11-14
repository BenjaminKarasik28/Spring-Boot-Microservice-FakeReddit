package com.example.postapi.service;

import com.example.postapi.model.Post;
import com.example.postapi.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostServiceImpl implements PostService {


    @Autowired
    PostRepository postRepository;


    @Override
    public Post createPost(Post post, String username) {
        post.setUsername(username);
        return postRepository.save(post);
    }
}
