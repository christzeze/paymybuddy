package com.openclassroooms.paymybuddy.dto;

import lombok.Data;

@Data
public class UserDto {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String iban;
    private String bank;
    private double sold;
}
