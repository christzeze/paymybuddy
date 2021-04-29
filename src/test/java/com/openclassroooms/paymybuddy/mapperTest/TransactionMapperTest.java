package com.openclassroooms.paymybuddy.mapperTest;

import com.openclassroooms.paymybuddy.dto.AccountDto;
import com.openclassroooms.paymybuddy.dto.BankDto;
import com.openclassroooms.paymybuddy.dto.TransactionDto;
import com.openclassroooms.paymybuddy.dto.UserDto;
import com.openclassroooms.paymybuddy.mapper.TransactionMapper;
import com.openclassroooms.paymybuddy.model.Account;
import com.openclassroooms.paymybuddy.model.Bank;
import com.openclassroooms.paymybuddy.model.Transaction;
import com.openclassroooms.paymybuddy.model.User;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.time.LocalDate;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class TransactionMapperTest {
    private final TransactionMapper transactionMapper = Mappers.getMapper(TransactionMapper.class);

    @Test
    public void shouldMapTransactionToDto() {
        //given
        User user = new User(2, "jacques", "martin", "jacquesmartin@gmail.com", "123456");
        Bank bank = new Bank("Crédit agricole melun nord", "123 albert Street", "77000", "Melun");
        Account account = new Account(3, user, bank, "bb123456789", 250, true);
        Transaction transaction = new Transaction(1, "transaction 1", 100, LocalDate.now(), "aa123456789", user, account);

        //when
        TransactionDto transactionDto = transactionMapper.toDTO(transaction);

        //then
        assertThat(transactionDto).isNotNull();
        assertThat(transactionDto.getAmount()).isEqualTo(100);
        assertThat(transactionDto.getDate()).isEqualTo(LocalDate.now());
        assertThat(transactionDto.getDesignation()).isEqualTo("transaction 1");
        assertThat(transactionDto.getEmitterIban()).isEqualTo("aa123456789");
        assertThat(transactionDto.getReceiver().getIban()).isEqualTo("bb123456789");
        assertThat(transactionDto.getEmitter().getLastName()).isEqualTo("martin");
    }

    @Test
    public void shouldMapTransactionToEntity() {
        //given
        UserDto userDto = new UserDto(2, "jacques", "martin", "jacquesmartin@gmail.com", "123456");
        BankDto bankDto = new BankDto(1, "Crédit agricole melun nord", "123 albert Street", "77000", "Melun");
        AccountDto accountDto = new AccountDto(3, "bb123456789", 250, bankDto, userDto, true);
        TransactionDto transactionDto = new TransactionDto("transaction 1", 100, LocalDate.now(), "aa123456789", userDto, accountDto);
        //when
        Transaction transaction = transactionMapper.toEntity(transactionDto);

        //then
        assertThat(transaction).isNotNull();
        assertThat(transaction.getAmount()).isEqualTo(100);
        assertThat(transaction.getDate()).isEqualTo(LocalDate.now());
        assertThat(transaction.getDesignation()).isEqualTo("transaction 1");
        assertThat(transaction.getEmitterIban()).isEqualTo("aa123456789");
        assertThat(transaction.getReceiver().getIban()).isEqualTo("bb123456789");
        assertThat(transaction.getEmitter().getLastName()).isEqualTo("martin");
    }
}
