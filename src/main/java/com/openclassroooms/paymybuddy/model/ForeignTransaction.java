package com.openclassroooms.paymybuddy.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
public class ForeignTransaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private double amount;
    private LocalDate date;
    private String designation;
    private String emitterIban;

    @ManyToOne()
    @JoinColumn(name = "userSender", referencedColumnName = "id")
    private User sender;
    @ManyToOne()
    @JoinColumn(name = "userReceiver", referencedColumnName = "id")
    private Contact receiver;

    public ForeignTransaction() {
    }

    public ForeignTransaction(int id, double amount, LocalDate date, String designation, String emitterIban, User sender, Contact receiver) {
        this.id = id;
        this.amount = amount;
        this.date = date;
        this.designation = designation;
        this.emitterIban = emitterIban;
        this.sender = sender;
        this.receiver = receiver;
    }
}
