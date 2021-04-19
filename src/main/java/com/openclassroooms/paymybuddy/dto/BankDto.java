package com.openclassroooms.paymybuddy.dto;

import lombok.Data;

@Data
public class BankDto {
    int id;
    private String name;
    private String address;
    private String zip;
    private String City;

    public BankDto(int id, String name, String address, String zip, String city) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.zip = zip;
        City = city;
    }

    public BankDto() {
    }
}
