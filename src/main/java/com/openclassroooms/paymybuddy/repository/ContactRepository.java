package com.openclassroooms.paymybuddy.repository;

import com.openclassroooms.paymybuddy.model.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactRepository extends JpaRepository<Contact,Integer> {
    public Contact findById(int id);
}
