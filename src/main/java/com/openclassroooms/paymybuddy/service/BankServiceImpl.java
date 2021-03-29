package com.openclassroooms.paymybuddy.service;

import com.openclassroooms.paymybuddy.model.Bank;
import com.openclassroooms.paymybuddy.repository.BankRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BankServiceImpl implements BankService {

    private BankRepository bankRepository;

    public BankServiceImpl(BankRepository bankRepository) {
        super();
        this.bankRepository = bankRepository;
    }

    @Override
    public Bank createAccount(Bank bank) {
        return bankRepository.save(bank);
    }

    @Override
    public void updateAccount(Bank bank) {
        bankRepository.save(bank);
    }

    @Override
    public List<Bank> findAll() {
        return bankRepository.findAll();
    }
}
