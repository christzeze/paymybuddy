package com.openclassroooms.paymybuddy.serviceTest;

import com.openclassroooms.paymybuddy.model.Account;
import com.openclassroooms.paymybuddy.model.Bank;
import com.openclassroooms.paymybuddy.model.Transaction;
import com.openclassroooms.paymybuddy.model.User;
import com.openclassroooms.paymybuddy.repository.AccountRepository;
import com.openclassroooms.paymybuddy.repository.TransactionRepository;
import com.openclassroooms.paymybuddy.service.impl.TransactionServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
@RunWith(MockitoJUnitRunner.class)
public class TransactionServiceTest {
    @InjectMocks
    private TransactionServiceImpl transactionService;

    @Mock
    private TransactionRepository transactionRepository;

    @Mock
    private AccountRepository accountRepository;

    @Test
    public void shouldThrowExceptionWhenNotEnoughMoneyOnAccount() throws Exception {
        //given
        User user = new User(1, "john", "doe", "johndoe@gmail.com", "123456");
        Bank bank = new Bank("Crédit agricole melun nord", "123 albert Street", "77000", "Melun");
        Account account = new Account(3, user, bank, "bb123456789", 250, true);
        Transaction transaction = new Transaction(1, "virement1", 100, LocalDate.of(2021, 4, 16), "bb123456789", user, account);

        try {
            when(transactionService.createTransaction(new Transaction())).thenThrow(new Exception("Not enough money on account"));
            transactionService.createTransaction(transaction);
        } catch (Exception e) {

            assertThat(e instanceof Exception).isEqualTo(true);
        }
    }

    @Test
    public void testCreateTransactionShouldReturnTransactionForNewTransactionAndUpdateSoldForReceiverIsEqualEmitter() throws Exception {

        //given

        User user = new User(1, "john", "doe", "johndoe@gmail.com", "123456");
        Bank bank = new Bank("Crédit agricole melun nord", "123 albert Street", "77000", "Melun");
        Account account = new Account(3, user, bank, "bb123456789", 250, true);


        Transaction transaction = new Transaction();
        transaction.setDate(LocalDate.now());
        transaction.setAmount(100);
        transaction.setDesignation("Transaction 1");
        transaction.setEmitterIban("bb123456789");
        transaction.setReceiver(account);
        transaction.setEmitter(user);

        when(accountRepository.findByIban(anyString())).thenReturn(account);
        when(accountRepository.save(account)).thenReturn(account);
        when(accountRepository.findById(anyInt())).thenReturn(java.util.Optional.of(account));
        when(transactionRepository.save(transaction)).thenReturn(transaction);

        //when
        Transaction createdTransaction = transactionService.createTransaction(transaction);


        //then
        assertThat((account.getSold())).isEqualTo(350);
        assertThat(createdTransaction.getDate()).isEqualTo(LocalDate.now());
        assertThat(createdTransaction.getAmount()).isEqualTo(100);
        assertThat(createdTransaction.getDesignation()).isEqualTo("Transaction 1");
        assertThat(createdTransaction.getEmitterIban()).isEqualTo("bb123456789");
        assertThat(createdTransaction.getReceiver().getUser().getLastName()).isEqualTo("doe");
        assertThat(createdTransaction.getEmitter().getLastName()).isEqualTo("doe");
    }

    @Test
    public void testCreateTransactionShouldReturnTransactionForNewTransactionForReceiverNotEqualEmitter() throws Exception {

        //given

        User userEmitter = new User(1, "john", "doe", "johndoe@gmail.com", "123456");
        User userReceiver = new User(2, "jacques", "martin", "jacquesmartin@gmail.com", "123456");
        Bank bank = new Bank("Crédit agricole melun nord", "123 albert Street", "77000", "Melun");
        Account account = new Account(3, userEmitter, bank, "bb123456789", 250, true);
        Account account2 = new Account(4, userReceiver, bank, "zz123456789", 100, true);


        Transaction transaction = new Transaction();
        transaction.setDate(LocalDate.now());
        transaction.setAmount(100);
        transaction.setDesignation("Transaction 1");
        transaction.setEmitterIban("bb123456789");
        transaction.setReceiver(account2);
        transaction.setEmitter(userEmitter);

        when(accountRepository.findByIban(anyString())).thenReturn(account);
        when(accountRepository.save(account)).thenReturn(account);
        when(accountRepository.findById(anyInt())).thenReturn(java.util.Optional.of(account2));
        when(transactionRepository.save(transaction)).thenReturn(transaction);

        //when
        Transaction createdTransaction = transactionService.createTransaction(transaction);


        //then
        assertThat((account.getSold())).isEqualTo(150);
        assertThat(createdTransaction.getDate()).isEqualTo(LocalDate.now());
        assertThat(createdTransaction.getAmount()).isEqualTo(100);
        assertThat(createdTransaction.getDesignation()).isEqualTo("Transaction 1");
        assertThat(createdTransaction.getEmitterIban()).isEqualTo("bb123456789");
        assertThat(createdTransaction.getReceiver().getUser().getLastName()).isEqualTo("martin");
        assertThat(createdTransaction.getEmitter().getLastName()).isEqualTo("doe");
    }
}
