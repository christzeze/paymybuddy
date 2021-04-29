package com.openclassroooms.paymybuddy.service;

import com.openclassroooms.paymybuddy.model.Bank;

import java.util.List;

public interface BankService {
    Bank createAccount(Bank bank);


    List<Bank> findAll();

}
