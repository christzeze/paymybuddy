package com.openclassroooms.paymybuddy.dto;

import lombok.Data;

@Data
public class UserRegistrationDto {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String iban;
    private String bank;
    private double sold;

    public UserRegistrationDto(){

    }

    public UserRegistrationDto(String firstName, String lastName, String email, String password, String iban, String bank, double sold) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.iban = iban;
        this.bank = bank;
        this.sold = sold;
    }


}
