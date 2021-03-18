package com.openclassroooms.paymybuddy.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Bank {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String address;
    private String zip;
    private String City;

    public Bank() {
    }

    public Bank(String name, String address, String zip, String city) {
        this.name = name;
        this.address = address;
        this.zip = zip;
        City = city;
    }
}
