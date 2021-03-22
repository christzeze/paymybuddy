package com.openclassroooms.paymybuddy.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
public class ForeignTransaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private double amount;
    private LocalDate date;
    @ManyToOne()
    @JoinColumn(name = "userSender", referencedColumnName = "userId")
    private Account userSender;
    @ManyToOne()
    @JoinColumn(name = "userReceiver", referencedColumnName = "id")
    private Contact userReceiver;

    public ForeignTransaction() {
    }
}
