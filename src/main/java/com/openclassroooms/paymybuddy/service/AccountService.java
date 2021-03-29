package com.openclassroooms.paymybuddy.service;

import com.openclassroooms.paymybuddy.dto.AccountDto;
import com.openclassroooms.paymybuddy.model.Account;
import com.openclassroooms.paymybuddy.model.Bank;
import com.openclassroooms.paymybuddy.model.User;

public interface AccountService {
    //public Account createAccount(AccountDto accountDto, User user, Bank bankId);
    public Account createAccount(AccountDto accountDto);
}
