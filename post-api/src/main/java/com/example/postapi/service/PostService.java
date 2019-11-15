package com.example.postapi.service;

import com.example.postapi.model.Post;
import com.example.postapi.model.PostComment;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;


public interface PostService {

    public Post createPost(Post post, String username);

    public Iterable<Post> getAllPosts();

    public Iterable<Post> getAllPostsByUsername(String username);

    public Long deletePostbyId(Long postId);

    public PostComment getAllCommentsByPostId(Long postId);

}
