package com.example.userapi.repository;


import com.example.userapi.model.Profile;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfileRepository extends CrudRepository<Profile, Long>{

    @Query("from Profile up inner join User u on u.username = ?1 and up.id = u.profile.id")
    public Profile findProfileByUsername(String username);
}