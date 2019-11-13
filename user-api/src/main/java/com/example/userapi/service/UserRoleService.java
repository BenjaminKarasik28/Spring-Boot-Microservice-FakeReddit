package com.example.userapi.service;

import com.example.userapi.model.User;
import com.example.userapi.model.UserRole;

import java.util.List;

public interface UserRoleService {

    public UserRole createRole(UserRole newRole);

    public List<UserRole> listRoles();

    public UserRole getRole(String roleName);

    public User getUser(String email);
}