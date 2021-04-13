package com.openclassroooms.paymybuddy.dto;

import lombok.Data;

@Data
public class ConnectionDto {
    private String lastName;
    private String email;
    private UserDto userDto;
}
