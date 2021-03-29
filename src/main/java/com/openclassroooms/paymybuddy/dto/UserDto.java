package com.openclassroooms.paymybuddy.dto;

import lombok.Data;

@Data
public class UserDto {
    int id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
}
