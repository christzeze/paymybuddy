package com.openclassroooms.paymybuddy.repository;

import com.openclassroooms.paymybuddy.model.Connection;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConnectionRepository extends JpaRepository<Connection, Integer> {
    Connection findByUserId(int id);
}
