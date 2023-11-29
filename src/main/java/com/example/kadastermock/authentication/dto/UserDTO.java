package com.example.kadastermock.authentication.dto;

import lombok.Data;

@Data
public class UserDTO {
    private String name;
    private String email;
    private String address;
    private String password;
}

