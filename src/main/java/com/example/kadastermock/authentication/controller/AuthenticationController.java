package com.example.kadastermock.authentication.controller;

import com.example.kadastermock.authentication.dto.LoginDTO;
import com.example.kadastermock.authentication.service.AuthenticationService;
import com.example.kadastermock.user_registration.dto.UserDTO;
import com.example.kadastermock.user_registration.model.UserRegistration;
import com.example.kadastermock.user_registration.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
@Controller
@RequestMapping("/auth")
public class AuthenticationController {
    @Autowired
    private UserService userService;

    @GetMapping("/user_registration")
    public String getRegisterPage(Model model) {
        model.addAttribute("registerRequest", new UserRegistration());
        return "user_registration";
    }

    @PostMapping("/user_registration")
    public String register(@ModelAttribute UserRegistration user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setName(user.getUsername());
        userDTO.setEmail(user.getEmail());
        userDTO.setPassword(user.getPassword());

        UserRegistration regUser = userService.registerUser(userDTO);
        return regUser == null ? "error_page" : "redirect:/auth/login";
    }


    @GetMapping("/login")
    public String getLoginPage(Model model) {
        model.addAttribute("loginRequest", new UserRegistration());
        return "login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute UserRegistration user, Model model) {
        UserRegistration loginUser = userService.authenticate(user.getUsername(), user.getPassword());
        if (loginUser != null) {
            model.addAttribute("userLogin", loginUser.getUsername());
            return "mypage";
        } else {
            return "error_page";
        }
    }
}
