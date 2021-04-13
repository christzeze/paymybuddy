package com.openclassroooms.paymybuddy.repository;

import com.openclassroooms.paymybuddy.model.*;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ForeignTransactionRepository extends JpaRepository<ForeignTransaction,Integer> {
    public Page<ForeignTransaction> findBySender(User user, Pageable pageable);


}
