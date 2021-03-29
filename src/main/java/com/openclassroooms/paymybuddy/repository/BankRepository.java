package com.openclassroooms.paymybuddy.repository;

import com.openclassroooms.paymybuddy.model.Bank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface BankRepository extends JpaRepository<Bank,Integer> {
    Bank findByName(String name);
}
