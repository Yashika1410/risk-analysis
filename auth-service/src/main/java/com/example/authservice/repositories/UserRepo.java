package com.example.authservice.repositories;

import org.springframework.data.repository.CrudRepository;

import com.example.authservice.entity.User;
/**
 * User repo interface.
 */
public interface UserRepo extends CrudRepository<User, Integer> {
    /**
     * checks if user by email already exists or not.
     * @param email
     * @return boolean if user by exists or not.
     */
    boolean existsByEmail(String email);
    /**
     * Get user details by email.
     * @param email
     * @return return user object by email.
     */
    User findByEmail(String email);

}
