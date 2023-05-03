package com.example.authservice.model;

import java.util.Date;

import lombok.Data;
/**
 * Token model class.
 */
@Data
public class TokenModel {
    /**
     * email of the user.
     */
    private String email;
    /**
     * issued at date which contains date at which token got generated.
     */
    private Date issuedAt;
    /**
     * expiration date which contains date at which token got expired.
     */
    private Date expiration;
}
