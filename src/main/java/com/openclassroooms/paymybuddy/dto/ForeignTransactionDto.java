package com.openclassroooms.paymybuddy.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ForeignTransactionDto {
    private String designation;
    private double amount;
    private LocalDate date;
    private AccountDto sender;
    private ContactDto receiver;
}
