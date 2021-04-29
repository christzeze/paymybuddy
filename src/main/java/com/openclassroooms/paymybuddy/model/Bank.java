package com.openclassroooms.paymybuddy.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

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

    //@OneToMany(cascade = {CascadeType.ALL})
    //List<Account> accounts;

    public Bank() {
    }

    public Bank(String name, String address, String zip, String city) {
        this.name = name;
        this.address = address;
        this.zip = zip;
        City = city;
    }
}
