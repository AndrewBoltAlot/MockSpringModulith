package com.example.kadastermock.boat_registration.model;

import com.example.kadastermock.user_registration.model.UserRegistration;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "boat_registration")
@Data
@NoArgsConstructor
public class Boat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String type;
    private BigDecimal cost;  // Added cost field

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserRegistration owner;

    // No need to explicitly include constructors, getters, and setters
}
