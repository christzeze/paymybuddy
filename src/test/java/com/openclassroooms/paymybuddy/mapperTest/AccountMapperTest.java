package com.openclassroooms.paymybuddy.mapperTest;

import com.openclassroooms.paymybuddy.dto.AccountDto;
import com.openclassroooms.paymybuddy.dto.BankDto;
import com.openclassroooms.paymybuddy.dto.UserDto;
import com.openclassroooms.paymybuddy.mapper.AccountMapper;
import com.openclassroooms.paymybuddy.mapper.BankMapper;
import com.openclassroooms.paymybuddy.model.Account;
import com.openclassroooms.paymybuddy.model.Bank;
import com.openclassroooms.paymybuddy.model.Contact;
import com.openclassroooms.paymybuddy.model.User;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class AccountMapperTest {
    private final AccountMapper accountMapper = Mappers.getMapper(AccountMapper.class);

    @Test
    public void shouldMapAccountToDto() {
        //given
        User user = new User(1,"john", "doe", "johndoe@gmail.com", "123456");
        Bank bank = new Bank("Crédit agricole melun nord", "123 albert Street", "77000", "Melun");
        Account account = new Account(3, user, bank, "bb123456789", 250, true);

        //when
        AccountDto accountDto = accountMapper.toDTO(account);

        //then
        assertThat(accountDto).isNotNull();
        assertThat(accountDto.getBank().getName()).isEqualTo("Crédit agricole melun nord");
        assertThat(accountDto.getIban()).isEqualTo("bb123456789");
        assertThat(accountDto.getSold()).isEqualTo(250);
        assertThat(accountDto.isUserAccount()).isEqualTo(true);
        assertThat(accountDto.getUser().getLastName()).isEqualTo("doe");

    }

    @Test
    public void shouldMapBankToEntity() {
        //given
        UserDto userDto = new UserDto(1,"john", "doe", "johndoe@gmail.com", "123456");
        BankDto bankDto = new BankDto(1,"Crédit agricole melun nord", "123 albert Street", "77000", "Melun");
        AccountDto accountDto=new AccountDto(3,"bb123456789",250,bankDto,userDto,true);
        //when
        Account account=accountMapper.toEntity(accountDto);

        //then
        assertThat(account).isNotNull();
        assertThat(account.getBank().getName()).isEqualTo("Crédit agricole melun nord");
        assertThat(account.getIban()).isEqualTo("bb123456789");
        assertThat(account.getSold()).isEqualTo(250);
        assertThat(account.isUserAccount()).isEqualTo(true);
        assertThat(account.getUser().getLastName()).isEqualTo("doe");
    }
}
