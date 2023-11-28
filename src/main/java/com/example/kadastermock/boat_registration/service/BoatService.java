package com.example.kadastermock.boat_registration.service;

import com.example.kadastermock.boat_registration.model.Boat;
import com.example.kadastermock.boat_registration.repository.BoatRepository;
import com.example.kadastermock.user_registration.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BoatService {
    private final BoatRepository boatRepository;
    private final UserRepository userRepository;

    @Autowired
    public BoatService(BoatRepository boatRepository, UserRepository userRepository) {
        this.boatRepository = boatRepository;
        this.userRepository = userRepository;
    }

    public Boat registerBoat(Boat boat, Long userId) {
        return userRepository.findById(userId).map(user -> {
            boat.setOwner(user);
            return boatRepository.save(boat);
        }).orElseThrow(() -> new IllegalArgumentException("Invalid user ID: " + userId));
    }

    public List<Boat> getAllBoats() {
        return boatRepository.findAll();
    }

    public void deleteBoat(Long boatId) {
        boatRepository.findById(boatId).ifPresent(boatRepository::delete);
    }
}
