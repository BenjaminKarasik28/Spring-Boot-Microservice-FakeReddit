package com.example.userapi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

/**
 * Represents user role.
 * A user can have many roles.
 */
@Entity
@Table(name = "role")
public class UserRole {

    @JsonIgnore
    @ManyToMany(mappedBy="roles")

    private List<User> users;

    /**
     * Gets users
     * @return list of users
     */
    public List<User> getUsers(){ return users; }

    /**
     * Sets users
     * @param users type Users
     */
    public void setUsers(List<User> users) { this.users = users; }

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(unique = true, nullable = true)
    private String name;

    /**
     * The default constructor
     */
    public UserRole() {}

    /**
     * Gets the ID
     * @return the auto-generated ID for this role
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the ID
     * @param id type id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets name of a role
     * @return the role name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the role
     * @param name type name of role
     */
    public void setName(String name) {
        this.name = name;
    }
}

