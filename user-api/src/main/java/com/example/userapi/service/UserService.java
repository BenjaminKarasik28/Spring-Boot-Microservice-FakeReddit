package com.example.userapi.service;


import com.example.userapi.exceptionhandling.EmailSignupException;
import com.example.userapi.exceptionhandling.ExistingUserSignupException;
import com.example.userapi.model.User;
//import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService {

    /**
     * Allows user with valid credentials to log in
     *
     * @param user the user to log into the system
     */
    public List<String> userLogin(User user);

    /**
     * Allows user to sign up with a complete and unique email address
     *
     * @param user user to sign up
     */
    public List<String> userSignup(User user) throws EmailSignupException, ExistingUserSignupException;

    /**
     * Role to be added to user
     *
     * @param username name of user
     * @param roleId id of role to be added to user
     */
    public User addRole(String username, int roleId);

    /**
     * Retrieves user by username
     *
     * @param username name of user
     * @return User object
     */
    public User getUser(String username);

    /**
     * Deletes user by username
     *
     * @param username name of user
     * @return user id of deleted user
     */
    public Long deleteUserByUsername(String username);

    /**
     * Update user by username
     *
     * @param username name of user
     * @param user updated user details
     * @return updated user object
     */
    public User updateUser(String username, User user);

    /**
     * Get email by username
     *
     * @param username name of user
     * @return email of the user
     */
    public String getEmailByUsername(String username);
}