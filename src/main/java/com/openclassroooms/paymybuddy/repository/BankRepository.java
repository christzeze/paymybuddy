package com.openclassroooms.paymybuddy.repository;

import com.openclassroooms.paymybuddy.model.Bank;
import org.springframework.data.repository.CrudRepository;

public interface BankRepository extends CrudRepository<Bank,Integer> {
    public Bank findByName(String name);
}
