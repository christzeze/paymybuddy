package com.openclassroooms.paymybuddy.serviceTest;

import com.openclassroooms.paymybuddy.model.Account;
import com.openclassroooms.paymybuddy.model.Bank;
import com.openclassroooms.paymybuddy.model.User;
import com.openclassroooms.paymybuddy.repository.AccountRepository;
import com.openclassroooms.paymybuddy.service.impl.AccountServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
@RunWith(MockitoJUnitRunner.class)

public class AccountServiceTest {
    @InjectMocks
    private AccountServiceImpl accountService;

    @Mock
    private AccountRepository accountRepository;

    @Test
    public void testCreateAccountShouldReturnNewBankForExistingAccount() {

        //given
        User user = new User(1, "john", "doe", "johndoe@gmail.com", "123456");
        Bank bank = new Bank("Crédit agricole melun nord", "123 albert Street", "77000", "Melun");

        Account account = new Account();
        account.setId(1);
        account.setUserAccount(true);
        account.setSold(20);
        account.setUser(user);
        account.setBank(bank);
        account.setIban("bb123456789");
        when(accountRepository.findByIban(account.getIban())).thenReturn(account);
        when(accountRepository.save(account)).thenReturn(account);

        //when
        Account createdAccount = accountService.createAccount(account);

        //then
        assertThat(createdAccount.getBank().getName()).isEqualTo("Crédit agricole melun nord");
        assertThat(createdAccount.getIban()).isEqualTo("bb123456789");
        assertThat(createdAccount.getSold()).isEqualTo(20);
        assertThat(createdAccount.getUser().getLastName()).isEqualTo("doe");

    }

    @Test
    public void testCreateAccountShouldoReturnAccountInformationsForNewAccount() {

        //given
        User user = new User(1, "john", "doe", "johndoe@gmail.com", "123456");
        Bank bank = new Bank("Crédit agricole melun nord", "123 albert Street", "77000", "Melun");

        Account account = new Account();
        account.setId(1);
        account.setUserAccount(true);
        account.setSold(20);
        account.setUser(user);
        account.setBank(bank);
        account.setIban("bb123");
        when(accountRepository.findByIban(account.getIban())).thenReturn(null);
        when(accountRepository.save(account)).thenReturn(account);

        //when
        Account createdAccount = accountService.createAccount(account);

        //then
        assertThat(createdAccount.getBank().getName()).isEqualTo("Crédit agricole melun nord");
        assertThat(createdAccount.getIban()).isEqualTo("bb123");
        assertThat(createdAccount.getSold()).isEqualTo(20);
        assertThat(createdAccount.getUser().getLastName()).isEqualTo("doe");


    }

    @Test
    public void ShouldReturnListOfAccount() {
        //given
        User user = new User(1, "john", "doe", "johndoe@gmail.com", "123456");
        Account account = new Account(3, new User(1, "john", "doe", "johndoe@gmail.com", "123456"), new Bank("Crédit agricole de melun sud", "123456 street", "77000", "MELUN"), "bb123456789", 20, true);
        List<Account> accounts = Collections.singletonList(account);
        when(accountRepository.findByUserId(user.getId())).thenReturn(accounts);

        //when
        List<Account> listofAccounts = accountService.listOfAccounts(user);

        //then
        assertThat(listofAccounts).isEqualTo(accounts);

    }

    @Test
    public void ShouldReturnListOfUserAccount() {
        //given
        List<Account> accounts = Arrays.asList(new Account(3, new User(1, "john", "doe", "johndoe@gmail.com", "123456"), new Bank("Crédit agricole de melun sud", "123456 street", "77000", "MELUN"), "bb123456789", 20, true));
        when(accountRepository.findByUserAccountIsTrue()).thenReturn(accounts);

        //when
        List<Account> listofAccounts = accountService.listOfUserAccounts();

        //then
        assertThat(listofAccounts).isEqualTo(accounts);

    }
}
