package com.openclassroooms.paymybuddy.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne(cascade = {CascadeType.MERGE})
    @JoinColumn(name = "userId", referencedColumnName = "id")
    private User user;
    @ManyToOne(cascade = {CascadeType.MERGE})
    @JoinColumn(name = "bankId", referencedColumnName = "id")
    private Bank bank;
    private String iban;
    private double sold;
    @Column(columnDefinition = "tinyint(1) default 0")
    private boolean userAccount;

    public Account(int id, User user, Bank bank, String iban, double sold, boolean userAccount) {
        this.id = id;
        this.user = user;
        this.bank = bank;
        this.iban = iban;
        this.sold = sold;
        this.userAccount = userAccount;
    }

    public Account() {
    }


}
