package com.example.authservice.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.ColumnTransformer;
import lombok.Data;

/**
 * User Entity class.
 */
@Data
@Entity
@Table(name = "users")
public class User {
    /**
     * unique id.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    /**
     * user unique email.
     */
    @Column(name = "email", nullable = false, unique = true)
    @ColumnTransformer(write = "LOWER(?)", read = "LOWER(email)")
    private String email;
    /**
     * user hashed password.
     */
    @Column(name = "password", nullable = false)
    private String password;

}
