package com.openclassroooms.paymybuddy.service;

import com.openclassroooms.paymybuddy.model.Account;
import com.openclassroooms.paymybuddy.repository.AccountRepository;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {
    public final AccountRepository accountRepository;

    public AccountServiceImpl(AccountRepository accountRepository) {
        super();
        this.accountRepository = accountRepository;
    }

    @Override
    public Account createAccount(Account account) {
        return accountRepository.save(account);
    }
}
