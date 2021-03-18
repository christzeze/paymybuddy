package com.openclassroooms.paymybuddy.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data

public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    String iban;
    double sold;
    @ManyToOne()
    @JoinColumn(name = "bankId", referencedColumnName = "id")
    private Bank bank;
    @ManyToOne()
    @JoinColumn(name="userId",referencedColumnName = "id")
    private User user;



    public Account(User user, Bank bank, String iban, double sold) {
        this.user = user;
        this.bank = bank;
        this.iban = iban;
        this.sold = sold;
    }
}
