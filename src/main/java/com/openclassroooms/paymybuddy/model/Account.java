package com.openclassroooms.paymybuddy.model;

import lombok.Data;
import org.hibernate.annotations.ManyToAny;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
public class Account implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    String iban;
    double sold;

    @ManyToOne()
    @JoinColumn(name = "bankId", referencedColumnName = "id")
    private Bank bank;

    @ManyToOne()
    @JoinColumn(name = "userId", referencedColumnName = "id")
    private User user;




}
