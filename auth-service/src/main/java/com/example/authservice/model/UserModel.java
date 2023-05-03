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
}
