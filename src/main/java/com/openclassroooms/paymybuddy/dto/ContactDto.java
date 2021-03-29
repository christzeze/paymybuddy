package com.openclassroooms.paymybuddy.dto;

import lombok.Data;

@Data
public class ContactDto {
    private int id;
    private String lastName;
    private String email;
    private String iban;
    private UserDto userDto;
}
