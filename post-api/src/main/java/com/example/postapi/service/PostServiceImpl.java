package com.example.postapi.service;

import com.example.postapi.model.Post;
import com.example.postapi.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class PostServiceImpl implements PostService {


    @Autowired
    PostRepository postRepository;


    @Override
    public Post createPost(Post post, String username) {
        post.setUsername(username);
        return postRepository.save(post);
    }

    @Override
    public Iterable<Post> getAllPosts() {
        return postRepository.findAll();
    }

    @Override
    public Iterable<Post> getAllPostById(Long userId) {

       return postRepository.findAllById(Collections.singleton(userId));

    }

    @Override
    public void deletePostbyId(Long postId) {
        postRepository.deleteById(postId);
    }


}
