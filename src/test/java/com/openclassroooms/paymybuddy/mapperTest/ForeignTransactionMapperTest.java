package com.openclassroooms.paymybuddy.mapperTest;

import com.openclassroooms.paymybuddy.dto.BankDto;
import com.openclassroooms.paymybuddy.dto.ContactDto;
import com.openclassroooms.paymybuddy.dto.ForeignTransactionDto;
import com.openclassroooms.paymybuddy.dto.UserDto;
import com.openclassroooms.paymybuddy.mapper.ContactMapper;
import com.openclassroooms.paymybuddy.mapper.ForeignTransactionMapper;
import com.openclassroooms.paymybuddy.model.*;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.time.LocalDate;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class ForeignTransactionMapperTest {
    private final ForeignTransactionMapper foreignTransactionMapper = Mappers.getMapper(ForeignTransactionMapper.class);

    @Test
    public void shouldMapForeignTransactionToDto() {
        //given
        User user = new User(2,"jacques", "martin", "jacquesmartin@gmail.com", "123456");
        Bank bank = new Bank("Crédit agricole melun nord", "123 albert Street", "77000", "Melun");
        Contact contact = new Contact(1, "doe", "johndoe@gmail.com", "bb123456789", user, bank);
        ForeignTransaction foreignTransaction=new ForeignTransaction(1,100, LocalDate.now(),"transaction 1","aa123456789",user,contact);

        //when
        ForeignTransactionDto foreignTransactionDto = foreignTransactionMapper.toDTO(foreignTransaction);

        //then
        assertThat(foreignTransactionDto).isNotNull();
        assertThat(foreignTransactionDto.getAmount()).isEqualTo(100);
        assertThat(foreignTransactionDto.getDate()).isEqualTo(LocalDate.now());
        assertThat(foreignTransactionDto.getDesignation()).isEqualTo("transaction 1");
        assertThat(foreignTransactionDto.getEmitterIban()).isEqualTo("aa123456789");
        assertThat(foreignTransactionDto.getReceiver().getLastName()).isEqualTo("doe");
        assertThat(foreignTransactionDto.getSender().getLastName()).isEqualTo("martin");
    }

    @Test
    public void shouldMapForeignTransactionToEntity() {
        //given
        UserDto userDto = new UserDto(2,"jacques", "martin", "jacquesmartin@gmail.com", "123456");
        BankDto bankDto = new BankDto(1,"Crédit agricole melun nord", "123 albert Street", "77000", "Melun");
        ContactDto contactDto = new ContactDto(1, "doe", "johndoe@gmail.com", "bb123456789", userDto, bankDto);
        ForeignTransactionDto foreignTransactionDto=new ForeignTransactionDto(100, LocalDate.now(),"transaction 1","aa123456789",userDto,contactDto);
        //when
        ForeignTransaction foreignTransaction=foreignTransactionMapper.toEntity(foreignTransactionDto);

        //then
        assertThat(foreignTransaction).isNotNull();
        assertThat(foreignTransaction.getAmount()).isEqualTo(100);
        assertThat(foreignTransaction.getDate()).isEqualTo(LocalDate.now());
        assertThat(foreignTransaction.getDesignation()).isEqualTo("transaction 1");
        assertThat(foreignTransaction.getEmitterIban()).isEqualTo("aa123456789");
        assertThat(foreignTransaction.getReceiver().getLastName()).isEqualTo("doe");
        assertThat(foreignTransaction.getSender().getLastName()).isEqualTo("martin");
    }
}
