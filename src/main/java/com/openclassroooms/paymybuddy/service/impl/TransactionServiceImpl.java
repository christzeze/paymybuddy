package com.openclassroooms.paymybuddy.service.impl;

import com.openclassroooms.paymybuddy.model.Account;
import com.openclassroooms.paymybuddy.model.Transaction;
import com.openclassroooms.paymybuddy.model.User;
import com.openclassroooms.paymybuddy.repository.AccountRepository;
import com.openclassroooms.paymybuddy.repository.TransactionRepository;
import com.openclassroooms.paymybuddy.repository.UserRepository;
import com.openclassroooms.paymybuddy.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.Optional;

@Service
@Transactional
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private UserRepository userRepository;


    @Override
    public Transaction createTransaction(Transaction transaction) {
        Account account = accountRepository.findByIban(transaction.getEmitterIban());
        Optional<Account> receiver = accountRepository.findById(transaction.getReceiver().getId());
        if (receiver.get().getIban() == account.getIban()) {
            account.setSold(account.getSold() + transaction.getAmount());
            accountRepository.save(account);
            transaction.setDate(LocalDate.now());
            return transactionRepository.save(transaction);
        } else {
            if (account.getSold() >= transaction.getAmount()) {
                account.setSold(account.getSold() - transaction.getAmount());
                accountRepository.save(account);
                transaction.setDate(LocalDate.now());
                return transactionRepository.save(transaction);
            } else {
                return null;
            }
        }
    }


    @Override
    public Page<Transaction> pagination(User user, int pageNo, int pageSize) {
        return transactionRepository.findByEmitter(user, PageRequest.of(pageNo, pageSize));
    }

}
