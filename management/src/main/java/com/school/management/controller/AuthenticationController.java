package com.school.management.controller;

import com.school.management.utils.response.ResponseHandler;
import com.school.management.configuration.JWTTokenHelper;
import com.school.management.model.AuthenticationRequest;
import com.school.management.model.person.UserLogin;
import com.school.management.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.text.MessageFormat;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;

    Logger logger = LoggerFactory.getLogger(AdminController.class);

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody AuthenticationRequest authenticationRequest)
    {
        final Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getEmail(), authenticationRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        UserLogin userLogin = (UserLogin) authentication.getPrincipal();
        JWTTokenHelper jwtTokenHelper = new JWTTokenHelper();

        try {
            String jwtToken = jwtTokenHelper.generateToken(userLogin.getUsername());
            userLogin.setPassword("");
            logger.info(MessageFormat.format("User with email id {0} logged succesfully", authenticationRequest.getEmail()));
            return ResponseHandler.generateResponse(HttpStatus.OK, "success", userLogin, jwtToken);
        } catch (InvalidKeySpecException | NoSuchAlgorithmException e) {
            logger.error(MessageFormat.format("Authentication failed with exception: {0}", e.getMessage()));
        }
        return null;
    }
}
