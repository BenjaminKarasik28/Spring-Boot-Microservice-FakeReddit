package com.example.userapi.service;

import com.example.userapi.model.User;
import com.example.userapi.model.UserRole;

import java.util.List;

public interface UserRoleService {

    /**
     * Creates and saves user role
     *
     * @param newRole the user role to be created
     * @return the newly created user role
     */
    public UserRole createRole(UserRole newRole);

    /**
     * Gets list of roles
     *
     * @return list of roles
     */
    public List<UserRole> listRoles();

    /**
     * Retrieve a specific role
     *
     * @param roleName name of role
     * @return specific role
     */
    public UserRole getRole(String roleName);

    /**
     * Get username by email
     *
     * @param email email address
     * @return user
     */
    public User getUser(String email);
}