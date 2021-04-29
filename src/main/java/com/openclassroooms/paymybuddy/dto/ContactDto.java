package com.openclassroooms.paymybuddy.dto;

import lombok.Data;

@Data
public class ContactDto {
    private int id;
    private String lastName;
    private String email;
    private String iban;
    private UserDto user;
    private BankDto bank;

    public ContactDto(int id, String lastName, String email, String iban, UserDto user, BankDto bank) {
        this.id = id;
        this.lastName = lastName;
        this.email = email;
        this.iban = iban;
        this.user = user;
        this.bank = bank;
    }

    public ContactDto() {
    }
}




