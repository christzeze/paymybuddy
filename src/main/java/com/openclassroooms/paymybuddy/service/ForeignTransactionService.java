package com.openclassroooms.paymybuddy.service;

import com.openclassroooms.paymybuddy.model.ForeignTransaction;
import com.openclassroooms.paymybuddy.model.User;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ForeignTransactionService {
    ForeignTransaction CreateTransaction(ForeignTransaction foreignTransaction) throws Exception;

    Page<ForeignTransaction> Pagination(User user, int pageNo, int pageSize);
}
