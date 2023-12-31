package com.example.kadastermock.user_registration.repository;

import com.example.kadastermock.authentication.controller.AuthenticationController;
import com.example.kadastermock.user_registration.model.UserRegistration;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserRegistration, Long> {
    Optional<UserRegistration> findByUsernameAndPassword(String username, String password);
    Optional<UserRegistration> findByUsername(String username);
}

