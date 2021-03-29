package com.openclassroooms.paymybuddy.repository;

import com.openclassroooms.paymybuddy.model.Bank;
import com.openclassroooms.paymybuddy.model.Contact;
import org.springframework.data.repository.CrudRepository;

public interface ContactRepository extends CrudRepository<Contact,Integer> {
    public Contact findByEmail(String email);
}
