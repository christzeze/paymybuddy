package com.openclassroooms.paymybuddy.mapper;


import com.openclassroooms.paymybuddy.dto.TransactionDto;
import com.openclassroooms.paymybuddy.model.Transaction;
import org.mapstruct.Mapper;

@Mapper(uses = {AccountMapper.class})
public interface TransactionMapper extends AbstractMapper<Transaction, TransactionDto> {
}
