package com.openclassroooms.paymybuddy.mapper;

import com.openclassroooms.paymybuddy.dto.AccountDto;
import com.openclassroooms.paymybuddy.model.Account;
import com.openclassroooms.paymybuddy.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AccountMapper extends AbstratMapper<Account, AccountDto> {
    @Autowired
    private BankMapper bankMapper;

    @Autowired
    private UserMapper userMapper;

    public AccountDto toDTO(Account account) {
        if (account == null) {
            return null;
        }
        AccountDto accountDto = new AccountDto();
        accountDto.setBank(bankMapper.toDTO(account.getBank()));
        accountDto.setIban(account.getIban());
        accountDto.setSold(account.getSold());
        return accountDto;
    }

    public Account toEntity(AccountDto account) {
        if (account == null) {
            return null;
        }
        Account accountEntity = new Account();
        accountEntity.setBank(bankMapper.toEntity(account.getBank()));
        //accountEntity.setBank(bankMapper.toEntity(null/*account.getBank()*/)); // todo changer String bank dans AccountDto vers BankDto
        accountEntity.setIban(account.getIban());
        accountEntity.setId(account.getId()); // todo setter le vrai ID
        accountEntity.setSold(account.getSold());
        accountEntity.setUser(userMapper.toEntity(account.getUser())); // todo utiliser UserMapper
        return accountEntity;
    }
}
