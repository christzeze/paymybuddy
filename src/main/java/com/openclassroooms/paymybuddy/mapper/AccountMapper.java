package com.openclassroooms.paymybuddy.mapper;

import com.openclassroooms.paymybuddy.dto.AccountDto;
import com.openclassroooms.paymybuddy.model.Account;
import org.mapstruct.Mapper;

@Mapper(uses = {BankMapper.class})
public interface AccountMapper extends AbstractMapper<Account, AccountDto> {
}
