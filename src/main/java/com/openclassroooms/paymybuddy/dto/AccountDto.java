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
}
