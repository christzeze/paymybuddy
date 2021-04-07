package com.openclassroooms.paymybuddy.model;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;


@Data
@Entity


public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String iban;
    private String bank;
    @OneToMany
    @JoinColumn(name = "userId")
    private List<Account> accounts;

    public User() {
    }

    public User(String firstName, String lastName, String email, String password, String iban, String bank) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.iban = iban;
        this.bank = bank;
    }

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    @Override
    public String toString() {
        return "User[id="+id+",firstName="+firstName+",lastName="+lastName+",email="+email+",password=  "+password+"]";
    }

}
