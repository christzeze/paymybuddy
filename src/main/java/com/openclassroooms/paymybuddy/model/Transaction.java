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
    private String emitterIban;
    @ManyToOne()
    @JoinColumn(name = "emitter", referencedColumnName = "id")
    private User emitter;
    @ManyToOne()
    @JoinColumn(name = "received", referencedColumnName = "id")
    private Account receiver;

    public Transaction() {
    }

    public Transaction(int id, String designation, double amount, LocalDate date, String emitterIban, User emitter, Account receiver) {
        this.id = id;
        this.designation = designation;
        this.amount = amount;
        this.date = date;
        this.emitterIban = emitterIban;
        this.emitter = emitter;
        this.receiver = receiver;
    }
}
