package com.openclassroooms.paymybuddy.repository;

import com.openclassroooms.paymybuddy.model.Account;
import com.openclassroooms.paymybuddy.model.Contact;
import com.openclassroooms.paymybuddy.model.ForeignTransaction;
import com.openclassroooms.paymybuddy.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ForeignTransactionRepository extends JpaRepository<ForeignTransaction,Integer> {
    public Page<ForeignTransaction> findBySender(Account id, Pageable pageable);

}
