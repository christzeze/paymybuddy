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
    private String designation;
    private double amount;
    private LocalDate date;
    @ManyToOne()
    @JoinColumn(name = "sender", referencedColumnName = "id")
    private Account sender;
    @ManyToOne()
    @JoinColumn(name = "receiver", referencedColumnName = "id")
    private Contact receiver;

    public ForeignTransaction() {
    }

    public ForeignTransaction(String designation, double amount, LocalDate date, Account sender, Contact receiver) {
        this.designation = designation;
        this.amount = amount;
        this.date = date;
        this.sender = sender;
        this.receiver = receiver;
    }
}
