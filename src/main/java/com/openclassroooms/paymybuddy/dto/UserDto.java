package com.openclassroooms.paymybuddy.dto;

import com.openclassroooms.paymybuddy.model.Account;
import lombok.Data;

import java.util.List;

@Data
public class UserDto {
    int id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private List<AccountDto> accounts;
}
