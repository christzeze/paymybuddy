package com.openclassroooms.paymybuddy.service;

import com.openclassroooms.paymybuddy.model.*;
import com.openclassroooms.paymybuddy.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class ForeignTransactionServiceImpl implements ForeignTransactionService {
    private ForeignTransactionRepository foreignTransactionRepository;

    @Autowired
    private AccountRepository accountRepository;

    public ForeignTransactionServiceImpl(ForeignTransactionRepository foreignTransactionRepository) {
        super();
        this.foreignTransactionRepository = foreignTransactionRepository;
    }

    @Override
    public ForeignTransaction CreateTransaction(ForeignTransaction foreignTransaction) {
        Account account=accountRepository.findByIban(foreignTransaction.getEmitterIban());
        if (account.getSold()>=foreignTransaction.getAmount()) {
            account.setSold(account.getSold() - foreignTransaction.getAmount());
            accountRepository.save(account);
            foreignTransaction.setDate(LocalDate.now());
            return foreignTransactionRepository.save(foreignTransaction);
        } else {
            return null;
        }

    }

    @Override
    public Page<ForeignTransaction> Pagination(User user,int pageNo,int pageSize) {
        return foreignTransactionRepository.findBySender(user, PageRequest.of(pageNo, pageSize));
    }
}
