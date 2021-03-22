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

    public Contact() {
    }
}
