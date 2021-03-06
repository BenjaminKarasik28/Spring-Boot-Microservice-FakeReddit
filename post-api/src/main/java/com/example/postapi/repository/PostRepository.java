package com.example.postapi.repository;


import com.example.postapi.model.Post;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface PostRepository extends CrudRepository<Post, Long> {

    @Transactional
    public void deleteByUsername(String username);

    public Iterable<Post> findAllByUsername(String username);

    public Post findByPostId(Long postId);
}
