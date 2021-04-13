package com.openclassroooms.paymybuddy.dto;

import com.openclassroooms.paymybuddy.model.User;
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
}
