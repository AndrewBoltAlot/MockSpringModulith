package com.example.kadastermock.boat_registration.controller;

import com.example.kadastermock.boat_registration.model.Boat;
import com.example.kadastermock.boat_registration.service.BoatService;
import com.example.kadastermock.user_registration.model.UserRegistration;
import com.example.kadastermock.user_registration.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/boat_registration")
public class BoatController {
    private final BoatService boatService;
    private final UserRepository userRepository;

    @Autowired
    public BoatController(BoatService boatService, UserRepository userRepository) {
        this.boatService = boatService;
        this.userRepository = userRepository;
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("boat", new Boat());

        // Fetch the list of all users
        List<UserRegistration> users = userRepository.findAll();
        model.addAttribute("users", users);

        return "boatRegistration";
    }

    @PostMapping
    public String registerBoat(@ModelAttribute("boat") Boat boat, @RequestParam("userId") Long userId, Model model) {
        try {
            boatService.registerBoat(boat, userId);
            return "redirect:/boat_registration/boats"; // Corrected redirect path
        } catch (IllegalArgumentException e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "boatRegistration";
        }
    }


    @GetMapping("/boats")
    public String listBoats(Model model) {
        List<Boat> boats = boatService.getAllBoats();
        model.addAttribute("boats", boats);
        return "boatList";
    }

    @GetMapping("/delete/{boatId}")
    public String deleteBoat(@PathVariable Long boatId) {
        boatService.deleteBoat(boatId);
        return "redirect:/boats";
    }
}
