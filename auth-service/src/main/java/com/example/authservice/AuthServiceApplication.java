package com.example.authservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main class.
 */
@SpringBootApplication
public class AuthServiceApplication {
    /**
     * protected constructor for checkkstyle.
    */
    protected AuthServiceApplication() {

    }
    /**
     * main function.
     * @param args
     */
    public static void main(final String[] args) {
      SpringApplication.run(AuthServiceApplication.class, args);
    }

}
