package com.openclassroooms.paymybuddy.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class TransactionDto {
    private String designation;
    private double amount;
    private LocalDate date;
    private AccountDto emitter;
    private AccountDto receiver;
}
