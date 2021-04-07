package com.openclassroooms.paymybuddy.repository;

import com.openclassroooms.paymybuddy.model.Account;
import com.openclassroooms.paymybuddy.model.ForeignTransaction;
import com.openclassroooms.paymybuddy.model.Transaction;
import com.openclassroooms.paymybuddy.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction,Integer> {
    public Page<Transaction> findByEmitter(Account id, Pageable pageable);
}
