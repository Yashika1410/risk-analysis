package com.example.authservice.model;

import lombok.Data;

/**
 * Auth model for user signIn and signUp response.
 */
@Data
public class AuthModel {
    /**
     * user email.
     */
    private String email;
    /**
     * User token which contains user details.
     */
    private String token;

}
