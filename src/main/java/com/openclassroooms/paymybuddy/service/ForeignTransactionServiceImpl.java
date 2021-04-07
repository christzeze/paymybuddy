package com.openclassroooms.paymybuddy.service;

import com.openclassroooms.paymybuddy.model.*;
import com.openclassroooms.paymybuddy.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
@Service
public class ForeignTransactionServiceImpl implements ForeignTransactionService {
    private ForeignTransactionRepository foreignTransactionRepository;

    public ForeignTransactionServiceImpl(ForeignTransactionRepository foreignTransactionRepository) {
        super();
        this.foreignTransactionRepository = foreignTransactionRepository;
    }

    @Autowired
    private ContactRepository contactRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AccountRepository accountRepository;


    @Override
    public ForeignTransaction CreateTransaction(ForeignTransaction foreignTransaction) {
        String userMail= SecurityContextHolder.getContext().getAuthentication().getName();
        User user=userRepository.findByEmail(userMail);
        Account userSender = accountRepository.findById(user.getId());
        foreignTransaction.setSender(userSender);
        //Account contact=accountRepository.findById(foreignTransaction.getReceiver().getId());
        Contact contact=contactRepository.findById(foreignTransaction.getReceiver().getId());
        foreignTransaction.setReceiver(contact);
        LocalDate date= LocalDate.now();
        foreignTransaction.setDate(date);
        return foreignTransactionRepository.save(foreignTransaction);

    }
}
