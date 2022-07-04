package com.school.management.controller;

import com.school.management.utils.response.ResponseHandler;
import com.school.management.configuration.jwt.JWTTokenHelper;
import com.school.management.model.AuthenticationRequest;
import com.school.management.model.person.UserLogin;
import com.school.management.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.text.MessageFormat;

import static com.school.management.utils.Constants.*;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;

    Logger logger = LoggerFactory.getLogger(AuthenticationController.class);

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody AuthenticationRequest authenticationRequest)
    {
        try
        {
            final Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getEmail(), authenticationRequest.getPassword()));

            SecurityContextHolder.getContext().setAuthentication(authentication);
            UserLogin userLogin = (UserLogin) authentication.getPrincipal();
            JWTTokenHelper jwtTokenHelper = new JWTTokenHelper();
            String jwtToken = jwtTokenHelper.generateToken(userLogin);
            userLogin.setPassword("");
            logger.info(MessageFormat.format(AuthenticationControllerConstants.USER_LOGGED_IN_SUCCESSFULLY, authenticationRequest.getEmail()));
            return ResponseHandler.generateResponse(HttpStatus.OK, ResponseMessageConstants.SUCCESS, userLogin, jwtToken);
        }
        catch (InvalidKeySpecException | NoSuchAlgorithmException |BadCredentialsException e)
        {
            logger.error(MessageFormat.format(AuthenticationControllerConstants.AUTHENTICATION_FAILED, e.getMessage()));
            return ResponseHandler.generateResponse(HttpStatus.UNAUTHORIZED, AuthenticationControllerConstants.BAD_CREDENTIALS,
            null, null);
        }
    }
}
