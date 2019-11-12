package com.example.userapi.repository;

import com.example.userapi.model.UserRole;
import org.springframework.data.repository.CrudRepository;

public interface UserRoleRepository extends CrudRepository<UserRole, Integer> {
    public UserRole findByName(String name);
}
