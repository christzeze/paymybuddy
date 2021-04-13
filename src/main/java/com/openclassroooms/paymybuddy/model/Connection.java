package com.openclassroooms.paymybuddy.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Connection {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String lastName;
    private String email;

    @ManyToOne()
    @JoinColumn(name = "emitterId", referencedColumnName = "id")
    private User user;
}
