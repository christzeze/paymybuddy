package com.openclassroooms.paymybuddy.service;

import com.openclassroooms.paymybuddy.model.Account;
import com.openclassroooms.paymybuddy.model.Contact;
import com.openclassroooms.paymybuddy.model.ForeignTransaction;
import com.openclassroooms.paymybuddy.model.Transaction;
import org.springframework.data.domain.Page;

public interface ForeignTransactionService {
    ForeignTransaction CreateTransaction(ForeignTransaction foreignTransaction);

}
