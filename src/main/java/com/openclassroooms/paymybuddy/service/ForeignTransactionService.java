package com.openclassroooms.paymybuddy.service;

import com.openclassroooms.paymybuddy.model.*;
import org.springframework.data.domain.Page;

public interface ForeignTransactionService {
    ForeignTransaction CreateTransaction(ForeignTransaction foreignTransaction);
    Page<ForeignTransaction> Pagination(User user, int pageNo, int pageSize);
}
