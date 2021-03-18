package com.openclassroooms.paymybuddy.dto;

import lombok.Data;

@Data
public class AccountDto {
    String iban;
    double sold;
    String bank;
}
