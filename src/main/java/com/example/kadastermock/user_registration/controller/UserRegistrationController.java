package com.example.kadastermock.user_registration.controller;

import com.example.kadastermock.user_registration.dto.UserDTO;
import com.example.kadastermock.user_registration.model.UserRegistration;
import com.example.kadastermock.user_registration.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class UserRegistrationController {

    private final UserService userService;

    @Autowired
    public UserRegistrationController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user_registration")
    public String showRegistrationForm(Model model) {
        model.addAttribute("userDto", new UserDTO());
        return "userRegistration";
    }

    @PostMapping("/user_registration")
    public String registerUser(@ModelAttribute("userDto") @Valid UserDTO userDto, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "userRegistration";
        }
        userService.registerUser(userDto);
        return "redirect:/auth/login"; // Redirect to login page after successful registration
    }

    @GetMapping("/users")
    public String listUsers(Model model) {
        List<UserRegistration> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "userDelete"; // The view that lists all users
    }

    @PostMapping("/users/delete/{id}")
    public String deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return "redirect:/users"; // Redirect back to the user list page after deletion
    }
}
