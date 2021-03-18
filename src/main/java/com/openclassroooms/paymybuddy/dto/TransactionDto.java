package com.openclassroooms.paymybuddy.dto;

import com.openclassroooms.paymybuddy.model.Contact;
import com.openclassroooms.paymybuddy.model.User;
import lombok.Data;

import java.time.LocalDate;

@Data
public class TransactionDto {
    private String designation;
    private double amount;
    private LocalDate date;
    private User userSender;
    private User userReceiver;
}
