package com.openclassroooms.paymybuddy.mapper;

import com.openclassroooms.paymybuddy.dto.ForeignTransactionDto;
import com.openclassroooms.paymybuddy.model.ForeignTransaction;
import org.mapstruct.Mapper;

@Mapper(uses = {ContactMapper.class})
public interface ForeignTransactionMapper extends AbstractMapper<ForeignTransaction, ForeignTransactionDto> {
}
