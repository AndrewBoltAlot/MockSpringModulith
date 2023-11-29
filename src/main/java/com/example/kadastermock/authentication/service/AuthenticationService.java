package com.example.kadastermock.authentication.service;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    private final ObjectProvider<AuthenticationManager> authenticationManagerProvider;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AuthenticationService(ObjectProvider<AuthenticationManager> authenticationManagerProvider, PasswordEncoder passwordEncoder) {
        this.authenticationManagerProvider = authenticationManagerProvider;
        this.passwordEncoder = passwordEncoder;
    }

    public boolean authenticate(String username, String password) {
        try {
            AuthenticationManager authenticationManager = authenticationManagerProvider.getObject();
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password)
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);
            return true;
        } catch (AuthenticationException e) {
            // Log the exception and/or handle it as per your application's requirements
            // For example, you can log the exception details for debugging purposes
            System.out.println("Authentication failed: " + e.getMessage());
            // Return false to indicate the authentication attempt was unsuccessful
            return false;
        }
    }
}
