package com.openclassroooms.paymybuddy.service;

import com.openclassroooms.paymybuddy.model.Transaction;
import com.openclassroooms.paymybuddy.model.User;
import org.springframework.data.domain.Page;

public interface TransactionService {
    Transaction createTransaction(Transaction transaction) throws Exception;

    Page<Transaction> pagination(User user, int pageNo, int pageSize);
}
