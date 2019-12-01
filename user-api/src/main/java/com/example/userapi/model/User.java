package com.example.userapi.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents user of the application.
 * A user can have multiple roles but only one profile.
 */
@Entity
@Table(name = "users")
public class User {

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {CascadeType.DETACH,
                    CascadeType.MERGE, CascadeType.REFRESH})
    @JoinTable(name = "user_role",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List<UserRole> roles;

    public List<UserRole> getRoles(){ return roles; }

    public void setRoles(List<UserRole> roles) { this.roles = roles; }

    public List<UserRole> addRole(UserRole userRole){
        if(roles == null)
            roles = new ArrayList<>();
        roles.add(userRole);

        return roles;
    }


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="profile_id")
    private Profile profile;

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "username", unique = true)
    private String username;

    @Column(name = "password")
    private String password;


    /**
     * The default constructor.
     */
    public User() {}

    /**
     * Gets the ID
     * @return the auto-generated ID for this user
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the ID
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets the username.
     * @return the username of user.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the username.
     * Username should be unique.
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Gets the password.
     * @return the password of user.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Gets the email.
     * @return the email of user.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email.
     * Email should be unique.
     */
    public void setEmail(String email) {
        this.email = email;
    }
}
