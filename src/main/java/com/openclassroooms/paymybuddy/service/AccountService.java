package com.openclassroooms.paymybuddy.service;

import com.openclassroooms.paymybuddy.model.Account;
import com.openclassroooms.paymybuddy.model.User;

import java.util.List;

public interface AccountService {
    Account createAccount(Account account);

    List<Account> listOfAccounts(User user);

    List<Account> listOfUserAccounts();
}
