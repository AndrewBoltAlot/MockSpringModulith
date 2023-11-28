package com.example.kadastermock.user_registration.service;

import com.example.kadastermock.user_registration.model.UserRegistration;
import com.example.kadastermock.user_registration.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public UserRegistration registerUser(UserRegistration userRegistration){
        log.info("Registring new user: {}", userRegistration);
        return userRepository.save(userRegistration);
    }

    public Optional<UserRegistration> findByEmail(String email){
        return userRepository.findByEmail(email);
    }

    public void deleteUser(Long userId){
        userRepository.deleteById(userId);
    }

    public List<UserRegistration> getAllUsers() {
        return userRepository.findAll();
    }
}
