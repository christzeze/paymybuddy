package com.openclassroooms.paymybuddy.repository;

import com.openclassroooms.paymybuddy.model.ForeignTransaction;
import com.openclassroooms.paymybuddy.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ForeignTransactionRepository extends JpaRepository<ForeignTransaction, Integer> {

    Page<ForeignTransaction> findBySender(User user, Pageable pageable);


}
