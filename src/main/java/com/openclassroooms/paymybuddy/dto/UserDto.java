package com.openclassroooms.paymybuddy.dto;

import lombok.Data;

@Data
public class UserDto {
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String iban;
    private BankDto bank;
    private double sold;
}
