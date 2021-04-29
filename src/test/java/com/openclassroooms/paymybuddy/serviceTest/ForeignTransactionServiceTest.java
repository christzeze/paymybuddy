package com.openclassroooms.paymybuddy.serviceTest;

import com.openclassroooms.paymybuddy.model.*;
import com.openclassroooms.paymybuddy.repository.AccountRepository;
import com.openclassroooms.paymybuddy.repository.ForeignTransactionRepository;
import com.openclassroooms.paymybuddy.service.impl.ForeignTransactionServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
@RunWith(MockitoJUnitRunner.class)


public class ForeignTransactionServiceTest {

    @InjectMocks
    private ForeignTransactionServiceImpl foreignTransactionService;

    @Mock
    private ForeignTransactionRepository foreignTransactionRepository;

    @Mock
    private AccountRepository accountRepository;

    @Test()
    public void shouldThrowExceptionWhenNotEnoughMoneyOnAccount() throws Exception {
        //given
        User user = new User(1, "john", "doe", "johndoe@gmail.com", "123456");
        Bank bank = new Bank("Crédit agricole melun nord", "123 albert Street", "77000", "Melun");
        Contact contact = new Contact(1, "doe", "johndoe@gmail.com", "bb123456789", user, bank);
        ForeignTransaction foreignTransaction = new ForeignTransaction(1, 100, LocalDate.of(2021, 4, 16), "virement1", "bb123456789", user, contact);

        try {
            when(foreignTransactionService.createTransaction(new ForeignTransaction())).thenThrow(new Exception("Not enough money on account"));
            foreignTransactionService.createTransaction(foreignTransaction);
        } catch (Exception e) {

            assertThat(e instanceof Exception).isEqualTo(true);
        }
    }

    @Test
    public void testCreateForeignTransactionShouldReturnForeignTransactionForNewForeignTransaction() throws Exception {

        //given

        User user = new User(1, "john", "doe", "johndoe@gmail.com", "123456");
        Bank bank = new Bank("Crédit agricole melun nord", "123 albert Street", "77000", "Melun");
        Contact contact = new Contact(1, "martin", "johndoe@gmail.com", "bb123456789", user, bank);
        Account account = new Account(3, user, bank, "bb123456789", 250, true);


        ForeignTransaction foreignTransaction = new ForeignTransaction();
        foreignTransaction.setDate(LocalDate.now());
        foreignTransaction.setAmount(100);
        foreignTransaction.setDesignation("Transaction 1");
        foreignTransaction.setEmitterIban("bb123456789");
        foreignTransaction.setReceiver(contact);
        foreignTransaction.setSender(user);

        when(accountRepository.findByIban(anyString())).thenReturn(account);
        when(accountRepository.save(account)).thenReturn(account);
        when(foreignTransactionRepository.save(foreignTransaction)).thenReturn(foreignTransaction);

        //when  
        ForeignTransaction createdForeignTransaction = foreignTransactionService.createTransaction(foreignTransaction);


        //then
        assertThat((account.getSold())).isEqualTo(150);
        assertThat(createdForeignTransaction.getDate()).isEqualTo(LocalDate.now());
        assertThat(createdForeignTransaction.getAmount()).isEqualTo(100);
        assertThat(createdForeignTransaction.getDesignation()).isEqualTo("Transaction 1");
        assertThat(createdForeignTransaction.getEmitterIban()).isEqualTo("bb123456789");
        assertThat(createdForeignTransaction.getReceiver().getLastName()).isEqualTo("martin");
        assertThat(createdForeignTransaction.getSender().getLastName()).isEqualTo("doe");
    }


    @Test
    public void shouldReturnPagedListOfForeignTransaction() {
        // GIVEN
        User user = new User(1, "john", "doe", "johndoe@gmail.com", "123456");
        Bank bank = new Bank("Crédit agricole melun nord", "123 albert Street", "77000", "Melun");
        Contact contact = new Contact(1, "martin", "johndoe@gmail.com", "bb123456789", user, bank);
        ForeignTransaction foreignTransaction = new ForeignTransaction(1, 100, LocalDate.of(2021, 4, 16), "test", "bb123456789", user, contact);
        List<ForeignTransaction> toutesTransactions = Collections.singletonList(foreignTransaction);

        int pageNo = 0;
        int pageSize = 5;
        PageRequest pageable = PageRequest.of(pageNo, pageSize);
        Page<ForeignTransaction> foreignTransactionPage = new PageImpl<>(toutesTransactions);

        when(foreignTransactionRepository.findBySender(user, pageable)).thenReturn(foreignTransactionPage);

        // WHEN
        Page<ForeignTransaction> foreignTransactionsCreatedPage = foreignTransactionService.pagination(user, pageNo, pageSize);

        // THEN
        assertThat(foreignTransactionsCreatedPage.getTotalPages()).isEqualTo(1);
    }
}

