package com.openclassroooms.paymybuddy.repository;

import com.openclassroooms.paymybuddy.model.User;
import org.springframework.data.repository.CrudRepository;


public interface UserRepository extends CrudRepository<User,Integer> {

    User findByEmail(String email);
    User findById(int id);
}
