package com.example.authservice.model;

import lombok.Data;

/**
 * User Model class for response.
 */
@Data
public class UserModel {
    /**
     * User email.
     */
    private String email;

    /**
     * User unhashed password.
     */
    private String password;

    /**
     * @param userEmail
     * @param userPassword
     */
    public UserModel(final String userEmail, final String userPassword) {
        this.email = userEmail;
        this.password = userPassword;
    }

}
