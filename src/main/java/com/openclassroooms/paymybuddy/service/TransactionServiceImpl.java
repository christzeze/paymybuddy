package com.openclassroooms.paymybuddy.service;

import com.openclassroooms.paymybuddy.dto.TransactionDto;
import com.openclassroooms.paymybuddy.mapper.AccountMapper;
import com.openclassroooms.paymybuddy.model.Transaction;
import com.openclassroooms.paymybuddy.model.User;
import com.openclassroooms.paymybuddy.repository.TransactionRepository;
import com.openclassroooms.paymybuddy.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class TransactionServiceImpl implements TransactionService {
    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AccountMapper accountMapper;

    @Override
    public Transaction save(String email, TransactionDto transactionDto) {
        User user = userRepository.findByEmail(email);
        int id_sender = user.getId();
        Transaction transaction = new Transaction(transactionDto.getDesignation(), transactionDto.getAmount(), transactionDto.getDate(),
                accountMapper.toEntity(transactionDto.getEmitter()), accountMapper.toEntity(transactionDto.getReceiver()));

        return transactionRepository.save(transaction);
    }


}
