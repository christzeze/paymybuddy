package com.openclassroooms.paymybuddy.util;

import com.openclassroooms.paymybuddy.model.Account;
import com.openclassroooms.paymybuddy.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Component
public class StringToAccount implements Converter<String, Account> {

    private AccountRepository accountRepository;

    public StringToAccount(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public Account convert(String arg0) {

        //Long id = Long.valueOf(arg0);
        int id = Integer.valueOf(arg0);
        return accountRepository.findById(id);
    }


}
