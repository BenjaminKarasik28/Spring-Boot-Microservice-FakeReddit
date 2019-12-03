package com.example.postapi.service;

import com.example.postapi.model.Post;
import com.example.postapi.model.PostComment;

import java.util.Optional;


public interface PostService {

    public Post createPost(Post post, String username);

    public Iterable<Post> getAllPosts();

    public Iterable<Post> getAllPostsByUsername(String username);

    public Long deletePostbyId(Long postId);

    public String deletePostByUsername(String username);

    public PostComment getAllCommentsByPostId(Long postId);

    public Optional<Post> getPostById(Long postId);

    public Post updatePost(Post post, Long postId);

    public String sendPostIdRestTemplate(Long postId);

}
