package com.example.kadastermock.user_registration.service;

import com.example.kadastermock.user_registration.dto.UserDTO;
import com.example.kadastermock.user_registration.model.UserRegistration;
import com.example.kadastermock.user_registration.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UserRegistration registerUser(UserDTO userDTO) {
        UserRegistration newUser = new UserRegistration();
        newUser.setUsername(userDTO.getName());
        newUser.setEmail(userDTO.getEmail());
        newUser.setAddress(userDTO.getAddress());
        newUser.setPassword(passwordEncoder.encode(userDTO.getPassword()));

        log.info("Registering new user: {}", newUser.getEmail());
        return userRepository.save(newUser);
    }
    public UserRegistration authenticate(String username, String password) {
        return userRepository.findByUsernameAndPassword(username, password).orElse(null);
    }

    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }

    public List<UserRegistration> getAllUsers() {
        return userRepository.findAll();
    }
}
