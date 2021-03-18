package com.openclassroooms.paymybuddy.service;

import com.openclassroooms.paymybuddy.dto.BankDto;
import com.openclassroooms.paymybuddy.model.Bank;
import com.openclassroooms.paymybuddy.model.User;

public interface BankService {
    Bank createAccount(BankDto bankDto);
    public void updateAccount(Bank bank);

}
