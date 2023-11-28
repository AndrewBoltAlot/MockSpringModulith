package com.example.kadastermock.user_registration.controller;

import com.example.kadastermock.user_registration.model.UserRegistration;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.example.kadastermock.user_registration.service.UserService;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/user_registration")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public String registerUser(@ModelAttribute("userRegistration") @Valid UserRegistration userRegistration, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "userRegistration";
        }

        try {
            userService.registerUser(userRegistration);
            return "redirect:/user_registration/users";
        } catch (IllegalArgumentException e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "userRegistration";
        }
    }


    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("userRegistration", new UserRegistration());
        return "userRegistration";
    }

    @PostMapping("/{id}")
    public String deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return "redirect:/user_registration/users";
    }


    @GetMapping("/users")
    public String listUsers(Model model) {
        List<UserRegistration> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "userDelete"; // The view that lists all users
    }

}
