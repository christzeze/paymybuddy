package com.openclassroooms.paymybuddy.dto;

import lombok.Data;

@Data
public class BankDto {
    int id;
    private String name;
    private String address;
    private String zip;
    private String City;

}
