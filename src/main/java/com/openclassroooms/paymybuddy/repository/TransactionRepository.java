package com.openclassroooms.paymybuddy.repository;

import com.openclassroooms.paymybuddy.model.Bank;
import com.openclassroooms.paymybuddy.model.Transaction;
import org.springframework.data.repository.CrudRepository;

public interface TransactionRepository extends CrudRepository<Transaction,Integer> {
    
}
