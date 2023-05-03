package com.example.authservice.controller;

import java.net.URISyntaxException;
import java.util.Calendar;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.authservice.entity.User;
import com.example.authservice.model.AuthModel;
import com.example.authservice.model.TokenModel;
import com.example.authservice.model.UserModel;
import com.example.authservice.service.UserJwtTokenService;
import com.example.authservice.service.UserService;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.io.IOException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping("/auth/api/v1")
public class UserController {
    /**
     * Autowired UserService class.
     */
    @Autowired
    private UserService userService;
    /**
     * Autowired User JWT token Service.
     */
    @Autowired
    private UserJwtTokenService userJwtTokenService;
    /**
     * variable which contains starting count of bearer token.
     */
    private static final int SUBSTRING_STARTING = 7;
    /**
     * Method that get called when sign up got hit.
     * @param user
     * @return returns auth model which contains user email and token.
     */
    @PostMapping("/sign-up")
    public AuthModel signUp(@RequestBody final UserModel user) {
        User newUser = userService.registerUser(user);
        AuthModel authModel = new AuthModel();
        authModel.setToken(userJwtTokenService.getToken(newUser));
        authModel.setEmail(newUser.getEmail().toLowerCase());
        return authModel;
    }

    /**
     * Method that get called when sig in got hit.
     * @param user
     * @return returns auth model which contains user email and token.
     */
    @PostMapping("/sign-in")
    public AuthModel signIn(@RequestBody final UserModel user) {
        User authUser = userService.loginUser(
            user.getEmail().toLowerCase(), user.getPassword());
        AuthModel authModel = new AuthModel();
        authModel.setToken(userJwtTokenService.getToken(authUser));
        authModel.setEmail(authUser.getEmail().toLowerCase());
        return authModel;
    }

    /**
     * Method that get called when verify got hit.
     * @param bearerToken
     * @return TokenModel object which contains email issue and expiry.
     */
    @GetMapping("/verify")
    @Operation(security = @SecurityRequirement(name = "bearerAuth"))
    public TokenModel validateToken(@Parameter(hidden = true)
    @RequestHeader(HttpHeaders.AUTHORIZATION) final String bearerToken) {
        if (!bearerToken.startsWith("Bearer")
        || bearerToken.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,
            "Token type is not bearer");
        }
        String token = bearerToken.substring(SUBSTRING_STARTING,
        bearerToken.length()).strip();
        try {
            Jws<Claims> userJws = userJwtTokenService.verify(token);
            Claims userClaim = userJws.getBody();
            TokenModel tokenModel = new TokenModel();
            tokenModel.setEmail(userClaim.getSubject());
            tokenModel.setIssuedAt(userClaim.getIssuedAt());
            tokenModel.setExpiration(userClaim.getExpiration());
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(new Date());
            calendar.add(Calendar.MINUTE, 1);
            if (tokenModel.getExpiration().getTime()
            < calendar.getTime().getTime()) {
            throw new ExpiredJwtException(userJws.getHeader(),
            userClaim, "token got expired");
            }
            return tokenModel;
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
            e.getMessage());
        } catch (ExpiredJwtException eJwt) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,
            eJwt.getLocalizedMessage());
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
            ex.getMessage());
        }
    }

}
