package com.openclassroooms.paymybuddy.service;

import com.openclassroooms.paymybuddy.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    User save(User user);
    User findUser();
}
