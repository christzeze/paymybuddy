package com.openclassroooms.paymybuddy.service;

import com.openclassroooms.paymybuddy.mapper.AccountMapper;
import com.openclassroooms.paymybuddy.model.Account;

import com.openclassroooms.paymybuddy.model.User;
import com.openclassroooms.paymybuddy.repository.AccountRepository;
import com.openclassroooms.paymybuddy.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService {
    public final AccountRepository accountRepository;

    public AccountServiceImpl(AccountRepository accountRepository) {
        super();
        this.accountRepository = accountRepository;
    }

    @Override
    public Account createAccount(Account account) {
        Account accountIban=accountRepository.findByIban(account.getIban());
        if (accountIban==null) {
            return accountRepository.save(account);
        } else {
            return null;
        }
    }

    @Override
    public List<Account> listOfAccounts(User user) {
        return accountRepository.findByUserId(user.getId());
    }

    @Override
    public List<Account> listOfUserAccounts() {
        return accountRepository.findByUserAccountIsTrue();
    }
}
