package com.openclassroooms.paymybuddy.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data

public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne()
    @JoinColumn(name = "userId", referencedColumnName = "id")
    private User userId;
    @ManyToOne()
    @JoinColumn(name = "bankId", referencedColumnName = "id")
    private Bank bankId;
    String iban;
    double sold;

    public Account(User userId, Bank bankId, String iban, double sold) {
        this.userId = userId;
        this.bankId = bankId;
        this.iban = iban;
        this.sold = sold;
    }
}
