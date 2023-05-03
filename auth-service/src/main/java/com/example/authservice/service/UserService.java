package com.example.authservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import com.example.authservice.entity.User;
import com.example.authservice.model.UserModel;
import com.example.authservice.repositories.UserRepo;
import at.favre.lib.crypto.bcrypt.BCrypt;


/**
 * User service class to perform operations for controller class.
 */
@Service
public class UserService {
    /**
     * Hashing cost.
     */
    private static final int HASH_COST = 10;
    /**
     * Autowired User repo interface for db operations.
     */
    @Autowired
    private UserRepo userRepo;

    /**
     * User Service method for creating new user and saving hash password.
     * @param user
     * @return User object.
     */
    public User registerUser(final UserModel user) {
        if (userRepo.existsByEmail(user.getEmail().toLowerCase())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT,
            "User Already Exits by this email");
        } else {
            user.setPassword(BCrypt.withDefaults().hashToString(HASH_COST,
            user.getPassword().toCharArray()));
            User newUser = new User();
            newUser.setEmail(user.getEmail());
            newUser.setPassword(user.getPassword());
            return userRepo.save(newUser);
        }
    }
    /**
     * User service method to chack user email and password and return user
     * details if valid.

     * @param email user email.
     * @param password user unhased password.
     * @return user details if user email and password are valid.
     */
    public final User loginUser(final String email, final String password) {
        if (userRepo.existsByEmail(email)) {
            User userDetails = userRepo.findByEmail(email);
            if (BCrypt.verifyer().verify(password.toCharArray(),
            userDetails.getPassword()) != null) {
                return userDetails;
            } else {
                throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,
                "Incorrect Password");
            }
        } else {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,
            "User Not Found");
        }

    }
}
