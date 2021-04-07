package com.openclassroooms.paymybuddy.repository;

import com.openclassroooms.paymybuddy.model.Account;
import com.openclassroooms.paymybuddy.model.Bank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface AccountRepository extends JpaRepository<Account,Integer> {
    Account findById(int id);

}
