package com.example.kadastermock.boat_registration.repository;

import com.example.kadastermock.boat_registration.model.Boat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoatRepository extends JpaRepository<Boat, Long> {
}
