package com.openclassroooms.paymybuddy.service.impl;

import com.openclassroooms.paymybuddy.model.Account;
import com.openclassroooms.paymybuddy.model.ForeignTransaction;
import com.openclassroooms.paymybuddy.model.User;
import com.openclassroooms.paymybuddy.repository.AccountRepository;
import com.openclassroooms.paymybuddy.repository.ForeignTransactionRepository;
import com.openclassroooms.paymybuddy.service.ForeignTransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;

@Service
@Transactional
public class ForeignTransactionServiceImpl implements ForeignTransactionService {
    @Autowired
    private ForeignTransactionRepository foreignTransactionRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public ForeignTransaction createTransaction(ForeignTransaction foreignTransaction) throws Exception {
        Account account = accountRepository.findByIban(foreignTransaction.getEmitterIban());
        if (account.getSold() >= foreignTransaction.getAmount()) {
            account.setSold(account.getSold() - foreignTransaction.getAmount());
            accountRepository.save(account);
            foreignTransaction.setDate(LocalDate.now());
            return foreignTransactionRepository.save(foreignTransaction);
        } else {
            throw new Exception("Not enough money on account");
        }

    }


    @Override
    public Page<ForeignTransaction> pagination(User user, int pageNo, int pageSize) {
        return foreignTransactionRepository.findBySender(user, PageRequest.of(pageNo, pageSize));
    }
}
