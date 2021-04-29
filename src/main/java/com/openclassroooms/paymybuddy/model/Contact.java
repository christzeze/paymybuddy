package com.openclassroooms.paymybuddy.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Contact {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String lastName;
    private String email;
    private String iban;
    @ManyToOne()
    @JoinColumn(name = "userId", referencedColumnName = "id")
    private User user;
    @ManyToOne()
    @JoinColumn(name = "bankId", referencedColumnName = "id")
    private Bank bank;

    public Contact() {
    }

    public Contact(int id, String lastName, String email, String iban, User user, Bank bank) {
        this.id = id;
        this.lastName = lastName;
        this.email = email;
        this.iban = iban;
        this.user = user;
        this.bank = bank;
    }
}
