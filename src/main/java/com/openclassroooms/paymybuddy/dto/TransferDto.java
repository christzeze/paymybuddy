package com.openclassroooms.paymybuddy.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class TransferDto {
    private String senderEmail;
    private String receiverEmail;
    private double amount;
    private String designation;
    private LocalDate date;
}
