package com.openclassroooms.paymybuddy.mapper;

import com.openclassroooms.paymybuddy.dto.BankDto;
import com.openclassroooms.paymybuddy.model.Bank;
import org.mapstruct.Mapper;

@Mapper
public interface BankMapper extends AbstractMapper<Bank, BankDto> {
}
