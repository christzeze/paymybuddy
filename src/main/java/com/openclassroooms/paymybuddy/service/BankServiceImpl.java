package com.openclassroooms.paymybuddy.service;

import com.openclassroooms.paymybuddy.dto.BankDto;
import com.openclassroooms.paymybuddy.model.Bank;
import com.openclassroooms.paymybuddy.repository.BankRepository;
import com.openclassroooms.paymybuddy.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class BankServiceImpl implements BankService{

    private BankRepository bankRepository;

    public BankServiceImpl(BankRepository bankRepository) {
        super();
        this.bankRepository = bankRepository;
    }

    @Override
    public Bank createAccount(BankDto bankDto) {
        Bank bank=new Bank(bankDto.getName(),bankDto.getAddress(),bankDto.getZip(),bankDto.getCity());
        Bank bankSaved=bankRepository.save(bank);
        return bankSaved;
    }

    @Override
    public void updateAccount(Bank bank) {

    }

}
