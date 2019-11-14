package com.example.postapi.repository;


import com.example.postapi.model.Post;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends CrudRepository<Post, Long> {
    public Iterable<Post> findAllByUsername(String username);
}
