package com.openclassroooms.paymybuddy.repository;

import com.openclassroooms.paymybuddy.model.Account;
import com.openclassroooms.paymybuddy.model.Bank;
import org.springframework.data.repository.CrudRepository;

public interface AccountRepository extends CrudRepository<Account,Integer> {
}
