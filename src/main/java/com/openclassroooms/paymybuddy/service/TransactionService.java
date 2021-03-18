package com.openclassroooms.paymybuddy.service;

import com.openclassroooms.paymybuddy.dto.TransactionDto;
import com.openclassroooms.paymybuddy.model.Transaction;

public interface TransactionService {
    Transaction save(String email,TransactionDto transactionDto);
}
