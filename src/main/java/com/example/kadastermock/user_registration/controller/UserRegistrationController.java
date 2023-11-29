package com.example.kadastermock.user_registration.controller;

import com.example.kadastermock.user_registration.dto.UserDTO;
import com.example.kadastermock.user_registration.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserRegistrationController {

    private final UserService userService;

    @Autowired
    public UserRegistrationController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user_registration")
    public String showRegistrationForm(Model model) {
        model.addAttribute("userDto", new UserDTO()); // Adding a UserDTO object to the model
        return "userRegistration";
    }

    @PostMapping("/user_registration")
    public String registerUser(UserDTO userDto) {
        userService.registerUser(userDto);
        // Redirect to an appropriate page after registration
        return "redirect:/login";
    }
}
