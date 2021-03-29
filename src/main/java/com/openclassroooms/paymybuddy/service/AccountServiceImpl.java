package com.openclassroooms.paymybuddy.service;

import com.openclassroooms.paymybuddy.dto.AccountDto;
import com.openclassroooms.paymybuddy.mapper.AccountMapper;
import com.openclassroooms.paymybuddy.model.Account;
import com.openclassroooms.paymybuddy.model.Bank;
import com.openclassroooms.paymybuddy.model.User;
import com.openclassroooms.paymybuddy.repository.AccountRepository;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {
    public AccountRepository accountRepository;
    public AccountMapper accountMapper;

    public AccountServiceImpl(AccountRepository accountRepository) {
        super();
        this.accountRepository = accountRepository;
    }

    @Override
    //public Account createAccount(AccountDto accountDto, User user, Bank bank) {
    public Account createAccount(AccountDto accountDto) {
        User user=accountMapper.toEntity(accountDto).getUser();
        Bank bank=accountMapper.toEntity(accountDto).getBank();
        Account account=new Account(user,bank,accountDto.getIban(),0);
        Account accountSaved=accountRepository.save(account);
        return accountSaved;
    }
}
