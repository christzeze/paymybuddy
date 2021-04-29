package com.openclassroooms.paymybuddy.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ForeignTransactionDto {
    private double amount;
    private LocalDate date;
    private String designation;
    private String emitterIban;
    private UserDto sender;
    private ContactDto receiver;

    public ForeignTransactionDto(double amount, LocalDate date, String designation, String emitterIban, UserDto sender, ContactDto receiver) {
        this.amount = amount;
        this.date = date;
        this.designation = designation;
        this.emitterIban = emitterIban;
        this.sender = sender;
        this.receiver = receiver;
    }

    public ForeignTransactionDto() {
    }
}
