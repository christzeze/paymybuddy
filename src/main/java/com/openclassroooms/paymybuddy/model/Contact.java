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
    private User aaUser;

    public Contact() {
    }

    public Contact(String lastName, String email, String iban) {
        this.lastName = lastName;
        this.email = email;
        this.iban = iban;
    }
}
