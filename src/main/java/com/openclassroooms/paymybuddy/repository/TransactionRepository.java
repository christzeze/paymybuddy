package com.openclassroooms.paymybuddy.repository;

import com.openclassroooms.paymybuddy.model.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction,Integer> {

   // Page<Transaction>findByEmitterIn(Collection<Account> accounts,Pageable pageable);
    Page<Transaction>findByEmitter(User user,Pageable pageable);

}
