package com.openclassroooms.paymybuddy.dto;

import lombok.Data;

@Data
public class AccountDto {
    private int id;
    private String iban;
    private double sold;
    private BankDto bank;
    private UserDto user;
    private boolean userAccount;

    public AccountDto(int id, String iban, double sold, BankDto bank, UserDto user, boolean userAccount) {
        this.id = id;
        this.iban = iban;
        this.sold = sold;
        this.bank = bank;
        this.user = user;
        this.userAccount = userAccount;
    }

    public AccountDto() {
    }
}
