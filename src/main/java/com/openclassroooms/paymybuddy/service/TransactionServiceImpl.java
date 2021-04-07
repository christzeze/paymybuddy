package com.openclassroooms.paymybuddy.service;

import com.openclassroooms.paymybuddy.dto.TransactionDto;
import com.openclassroooms.paymybuddy.mapper.AccountMapper;
import com.openclassroooms.paymybuddy.model.Account;
import com.openclassroooms.paymybuddy.model.Transaction;
import com.openclassroooms.paymybuddy.model.User;
import com.openclassroooms.paymybuddy.repository.AccountRepository;
import com.openclassroooms.paymybuddy.repository.TransactionRepository;
import com.openclassroooms.paymybuddy.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class TransactionServiceImpl implements TransactionService {

    private TransactionRepository transactionRepository;

    public TransactionServiceImpl(TransactionRepository transactionRepository) {
        super();
        this.transactionRepository = transactionRepository;
    }

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private UserRepository userRepository;


    @Override
    public Transaction CreateTransaction(Transaction transaction) {
        String userMail= SecurityContextHolder.getContext().getAuthentication().getName();
        User user=userRepository.findByEmail(userMail);
        Account userSender = accountRepository.findById(user.getId());
        transaction.setEmitter(userSender);
        //Account iban = accountRepository.findById(transaction.getIban().getId());
        Account receiver=accountRepository.findById(transaction.getReceiver().getId());
        //transaction.setIban(iban);
        transaction.setReceiver(receiver);
        LocalDate date= LocalDate.now();
        transaction.setDate(date);
        return transactionRepository.save(transaction);

    }


}
