package com.spring.login.registration.service;

import com.spring.login.registration.model.*;
import com.spring.login.registration.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    /**
     * This method takes the data provided in a RegisterRequest, creates a new user in the database, generates a JWT token for the user,
     * and returns that token in a response encapsulated in a TokenResponse instance.
     * This process is typically used in creating user accounts and generating JWT tokens to allow authenticated users to access protected resources in the application.
     */
    public TokenResponse register(RegisterRequest request) {
        //We set the values from userDetails with our entity values in DB
        var user = UserEntity.builder()
                .name(request.getFirstname())
                .lastname(request.getLastname())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .roles(EnumRole.USER)
                .location(request.getLocation())
                .build();
        //We save the user in DB
        userRepository.save(user);
        //We generate the token for this user
        var token = jwtService.generateToken(user);
        //return the token
        return TokenResponse.builder()
                .token(token).build();
    }

    /**
     * This method takes an email and password provided in an authentication request (AuthenticationRequest), authenticates the user using the authenticationManager,
     * generates a JWT token for the authenticated user and returns that token in a response encapsulated in an instance of TokenResponse.
     * This process is used to allow authenticated users to access protected resources in the application.
     */
    public TokenResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var user = userRepository.findByEmail(request.getEmail())
                .orElseThrow();
        //We generate the token for this user
        var token = jwtService.generateToken(user);
        //return the token
        return TokenResponse.builder()
                .token(token).build();
    }
}
