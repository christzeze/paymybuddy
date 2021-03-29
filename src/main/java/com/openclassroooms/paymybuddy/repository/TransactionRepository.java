package com.openclassroooms.paymybuddy.repository;

import com.openclassroooms.paymybuddy.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction,Integer> {

}
