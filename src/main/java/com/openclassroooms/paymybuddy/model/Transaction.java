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
    @JoinColumn(name = "emitterId", referencedColumnName = "id")
    private Account emitter;
    //@ManyToOne()
    //@JoinColumn(name = "iban", referencedColumnName = "iban")
    //private Account iban;
    @ManyToOne()
    @JoinColumn(name = "receiverId", referencedColumnName = "id")
    private Account receiver;

    public Transaction() {
    }

    public Transaction(String designation, double amount, LocalDate date, Account sender, Account receiver) {
        this.designation = designation;
        this.amount = amount;
        this.date = date;
        this.emitter = sender;
        this.receiver = receiver;
    }
}
