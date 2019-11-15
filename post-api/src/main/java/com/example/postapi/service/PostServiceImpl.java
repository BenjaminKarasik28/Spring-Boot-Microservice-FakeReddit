package com.example.postapi.service;

import com.example.postapi.model.Post;
import com.example.postapi.model.PostComment;
import com.example.postapi.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class PostServiceImpl implements PostService {

    RestTemplate restTemplate = new RestTemplate();

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
    public Iterable<Post> getAllPostsByUsername(String username) {

       return postRepository.findAllByUsername(username);

    }

    @Override
    public Long deletePostbyId(Long postId) {
        restTemplate.delete("http://localhost:8083/post/" + postId);

        postRepository.deleteById(postId);
        return postId;
    }

    @Override
    public PostComment getAllCommentsByPostId(Long postId) {
        return restTemplate.getForObject("http://localhost:8083/" + postId +"/list", PostComment.class);
    }


}
