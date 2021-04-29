package com.openclassroooms.paymybuddy.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class TransactionDto {
    private String designation;
    private double amount;
    private LocalDate date;
    private String emitterIban;
    private UserDto emitter;
    private AccountDto receiver;

    public TransactionDto(String designation, double amount, LocalDate date, String emitterIban, UserDto emitter, AccountDto receiver) {
        this.designation = designation;
        this.amount = amount;
        this.date = date;
        this.emitterIban = emitterIban;
        this.emitter = emitter;
        this.receiver = receiver;
    }

    public TransactionDto() {
    }
}


