package com.openclassroooms.paymybuddy.service.impl;

import com.openclassroooms.paymybuddy.model.Bank;
import com.openclassroooms.paymybuddy.repository.BankRepository;
import com.openclassroooms.paymybuddy.service.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class BankServiceImpl implements BankService {

    @Autowired
    private BankRepository bankRepository;

    @Override
    public Bank createAccount(Bank bank) {
        Bank bankName=bankRepository.findByName(bank.getName());
        if (bankName==null) {
            return bankRepository.save(bank);
        }
        else {
            bankName.setName(bank.getName());
            bankName.setAddress(bank.getAddress());
            bankName.setCity(bank.getCity());
            bankName.setZip(bank.getZip());
            return bankRepository.save(bankName);
        }
    }


    @Override
    public List<Bank> findAll() {
        return bankRepository.findAll();
    }
}
