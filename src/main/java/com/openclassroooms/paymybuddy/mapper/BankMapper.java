package com.openclassroooms.paymybuddy.mapper;

import com.openclassroooms.paymybuddy.dto.BankDto;
import com.openclassroooms.paymybuddy.model.Bank;
import org.springframework.stereotype.Component;

@Component
public class BankMapper extends AbstratMapper<Bank, BankDto> {
    @Override
    public Bank toEntity(BankDto dto) {
        return null;
    }

    @Override
    public BankDto toDTO(Bank entity) {
        return null;
    }
}
