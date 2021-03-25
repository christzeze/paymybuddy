package com.openclassroooms.paymybuddy.mapper;

import com.openclassroooms.paymybuddy.dto.BankDto;
import com.openclassroooms.paymybuddy.model.Bank;
import org.springframework.stereotype.Component;

@Component
public class BankMapper extends AbstratMapper<Bank, BankDto> {
    @Override
    public Bank toEntity(BankDto dto) {

        if(dto==null) {
            return null;
        }
        Bank bankEntity=new Bank();
        bankEntity.setName(dto.getName());
        bankEntity.setAddress(dto.getAddress());
        bankEntity.setZip(dto.getZip());
        bankEntity.setCity(dto.getCity());
        bankEntity.setId(dto.getId());
        return bankEntity;
    }

    @Override
    public BankDto toDTO(Bank entity) {

        if(entity==null) {
            return null;
        }
        BankDto bankDto=new BankDto();
        bankDto.setName(entity.getName());
        bankDto.setAddress(entity.getAddress());
        bankDto.setZip(entity.getZip());
        bankDto.setCity(entity.getCity());
        return bankDto;
    }
}
