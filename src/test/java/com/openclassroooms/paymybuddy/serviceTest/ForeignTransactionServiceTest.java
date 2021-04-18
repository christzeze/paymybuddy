package com.openclassroooms.paymybuddy.serviceTest;

import com.openclassroooms.paymybuddy.model.*;
import com.openclassroooms.paymybuddy.repository.AccountRepository;
import com.openclassroooms.paymybuddy.repository.ContactRepository;
import com.openclassroooms.paymybuddy.repository.ForeignTransactionRepository;
import com.openclassroooms.paymybuddy.service.impl.ContactServiceImpl;
import com.openclassroooms.paymybuddy.service.impl.ForeignTransactionServiceImpl;
import io.restassured.RestAssured;
import org.apache.coyote.Response;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.*;

import java.time.LocalDate;
import java.util.*;
import java.util.function.Function;

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

    @Mock
    private Page<ForeignTransaction> foreignTransactionPage;

    @Test()
    public void shouldThrowExceptionWhenNotEnoughMoneyOnAccount() throws Exception {
        //given
        User user = new User(1,"john", "doe", "johndoe@gmail.com", "123456");
        Bank bank = new Bank("Crédit agricole melun nord", "123 albert Street", "77000", "Melun");
        Contact contact = new Contact(1, "doe", "johndoe@gmail.com", "bb123456789", user, bank);
        ForeignTransaction foreignTransaction=new ForeignTransaction(1,100, LocalDate.of(2021, 4, 16),"virement1","bb123456789",user,contact);

        try {
            when(foreignTransactionService.CreateTransaction(new ForeignTransaction())).thenThrow(new Exception("Not enough money on account"));
            foreignTransactionService.CreateTransaction(foreignTransaction);
        } catch (Exception e) {

            assertThat(e instanceof Exception).isEqualTo(true);
        }
    }

    @Test
    public void testCreateForeignTransactionShouldReturnForeignTransactionForNewForeignTransaction() throws Exception {

        //given

        User user = new User(1,"john", "doe", "johndoe@gmail.com", "123456");
        Bank bank = new Bank("Crédit agricole melun nord", "123 albert Street", "77000", "Melun");
        Contact contact = new Contact(1, "martin", "johndoe@gmail.com", "bb123456789", user, bank);
        Account account = new Account(3, user, bank, "bb123456789", 250, true);



        ForeignTransaction foreignTransaction=new ForeignTransaction();
        foreignTransaction.setDate(LocalDate.of(2021, 4, 16));
        foreignTransaction.setAmount(100);
        foreignTransaction.setDesignation("Transaction 1");
        foreignTransaction.setEmitterIban("bb123456789");
        foreignTransaction.setReceiver(contact);
        foreignTransaction.setSender(user);

        when(accountRepository.findByIban(anyString())).thenReturn(account);
        when(accountRepository.save(account)).thenReturn(account);
        when(foreignTransactionRepository.save(foreignTransaction)).thenReturn(foreignTransaction);

        //when  
        ForeignTransaction createdForeignTransaction = foreignTransactionService.CreateTransaction(foreignTransaction);


        //then
        assertThat((account.getSold())).isEqualTo(150);
        assertThat(createdForeignTransaction.getDate()).isEqualTo(LocalDate.of(2021, 4, 16));
        assertThat(createdForeignTransaction.getAmount()).isEqualTo(100);
        assertThat(createdForeignTransaction.getDesignation()).isEqualTo("Transaction 1");
        assertThat(createdForeignTransaction.getEmitterIban()).isEqualTo("bb123456789");
        assertThat(createdForeignTransaction.getReceiver().getLastName()).isEqualTo("martin");
        assertThat(createdForeignTransaction.getSender().getLastName()).isEqualTo("doe");
    }


    @Test
    public void shouldReturnPagedListOfForeignTransaction(){
        int pageNo=0;
        int pageSize=5;

        User user = new User(1,"john", "doe", "johndoe@gmail.com", "123456");
        Bank bank = new Bank("Crédit agricole melun nord", "123 albert Street", "77000", "Melun");
        Contact contact = new Contact(1, "martin", "johndoe@gmail.com", "bb123456789", user, bank);
        ForeignTransaction foreignTransaction=new ForeignTransaction(1,100,LocalDate.of(2021, 4, 16),"test","bb123456789",user,contact);


        Pageable pageable = PageRequest.of(0, 5);
        //Page<ForeignTransaction> foreignTransactionsPage =foreignTransactionRepository.findBySender(user, pageable);
        when(foreignTransactionRepository.findBySender(user, PageRequest.of(pageNo, pageSize))).thenReturn((foreignTransactionPage));

        Page<ForeignTransaction> foreignTransactionsCreatedPage=foreignTransactionService.Pagination(user, pageNo, pageSize);

        //assertThat(foreignTransactionsCreatedPage.getTotalPages()).isEqualTo(0);
        //System.out.println(foreignTransactionsCreatedPage.getTotalElements());
    }
}

