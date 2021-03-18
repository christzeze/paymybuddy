package com.openclassroooms.paymybuddy.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String designation;
    private double amount;
    private LocalDate date;
    @ManyToOne()
    @JoinColumn(name = "userSender", referencedColumnName = "id")
    private User userSender;
    @ManyToOne()
    @JoinColumn(name = "userReceiver", referencedColumnName = "id")
    private User userReceiver;

    public Transaction(String designation, double amount, LocalDate date, User userSender, User userReceiver) {
        this.designation = designation;
        this.amount = amount;
        this.date = date;
        this.userSender = userSender;
        this.userReceiver = userReceiver;
    }
}
