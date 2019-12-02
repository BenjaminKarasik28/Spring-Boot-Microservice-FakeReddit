package com.example.userapi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

/**
 * Represents user profile. A user can only have one profile.
 */
@Entity
@Table(name = "profile")
public class Profile {

    @JsonIgnore
    @OneToOne(mappedBy = "profile", cascade={CascadeType.DETACH,
            CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    private User user;

    /**
     * Get user
     * @return user
     */
    public User getUser() {
        return user;
    }

    /**
     * Set user
     * @return list of user
     */
    public void setUser(User user) {
        this.user = user;
    }

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String email;

    @Column
    private String mobile;

    @Column
    private String address;

    /**
     * The default constructor.
     */
    public Profile() {}

    /**
     * Gets the ID
     * @return the auto-generated ID for this profile
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
     * Gets an additional email.
     * @return the additional email of user.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the addtional email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets the mobile number.
     * @return the mobile of user.
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * Sets mobile number
     */
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    /**
     * Gets the address.
     * @return the address of user.
     */
    public String getAddress() {
        return address;
    }

    /**
     * Sets address
     */
    public void setAddress(String address) {
        this.address = address;
    }

}