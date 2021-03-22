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
    private User user;
    @ManyToOne()
    @JoinColumn(name = "bankId", referencedColumnName = "id")
    private Bank bank;
    String iban;
    double sold;

    public Account() {
    }

    public Account(User user, Bank bankId, String iban, double sold) {
        this.user = user;
        this.bank = bankId;
        this.iban = iban;
        this.sold = sold;
    }
}
