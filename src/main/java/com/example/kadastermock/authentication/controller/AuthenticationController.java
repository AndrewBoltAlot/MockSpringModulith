package com.example.kadastermock.authentication.controller;

import com.example.kadastermock.authentication.dto.LoginDTO;
import com.example.kadastermock.authentication.service.AuthenticationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@Slf4j
@RequestMapping("/auth")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @Autowired
    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @GetMapping("/login")
    public ModelAndView login() {
        return new ModelAndView("login", "loginDTO", new LoginDTO());
    }

    @PostMapping("/login")
    public String performLogin(@ModelAttribute LoginDTO loginDTO) {
        log.info("Attempting login for user: {}", loginDTO.getEmail());
        boolean isAuthenticated = authenticationService.authenticate(loginDTO.getEmail(), loginDTO.getPassword());
        if (isAuthenticated) {
            return "redirect:/home";
        } else {
            return "redirect:/auth/login?error=true";
        }
    }

    @GetMapping("/logout")
    public String logout() {
        // Implement logout logic if required, e.g., invalidate session
        return "redirect:/public";
    }
}
