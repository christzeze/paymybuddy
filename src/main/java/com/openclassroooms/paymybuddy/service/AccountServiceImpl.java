package com.openclassroooms.paymybuddy.service;

import com.openclassroooms.paymybuddy.mapper.AccountMapper;
import com.openclassroooms.paymybuddy.model.Account;
import com.openclassroooms.paymybuddy.model.User;
import com.openclassroooms.paymybuddy.repository.AccountRepository;
import com.openclassroooms.paymybuddy.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {
    public final AccountRepository accountRepository;

    @Autowired
    private UserRepository userRepository;


    private AccountMapper accountMapper;

    public AccountServiceImpl(AccountRepository accountRepository) {
        super();
        this.accountRepository = accountRepository;
    }

    @Override
    public Account createAccount(Account account) {
        String userMail= SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByEmail(userMail);
        account.setUser(user);
        Account accountSaved=accountRepository.save(account);
        return accountSaved;
    }
}
