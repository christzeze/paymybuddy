package com.openclassroooms.paymybuddy.repository;

import com.openclassroooms.paymybuddy.model.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User,Integer> {

    User findByEmail(String email);
    User findById(int id);
}
