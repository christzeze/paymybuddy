package com.openclassroooms.paymybuddy.mapperTest;

import com.openclassroooms.paymybuddy.dto.BankDto;
import com.openclassroooms.paymybuddy.mapper.BankMapper;
import com.openclassroooms.paymybuddy.model.Bank;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class BankMapperTest {
    private final BankMapper bankMapper = Mappers.getMapper(BankMapper.class);

    @Test
    public void shouldMapBankToDto() {
        //given
        Bank bank = new Bank("Crédit agricole melun nord", "123 albert Street", "77000", "Melun");

        //when
        BankDto bankDto = bankMapper.toDTO(bank);

        //then
        assertThat(bankDto).isNotNull();
        assertThat(bankDto.getName()).isEqualTo("Crédit agricole melun nord");
        assertThat(bankDto.getAddress()).isEqualTo("123 albert Street");
        assertThat(bankDto.getZip()).isEqualTo("77000");
        assertThat(bankDto.getCity()).isEqualTo("Melun");
    }

    @Test
    public void shouldMapBankToEntity() {
        //given
        BankDto bankdto = new BankDto(1, "Crédit agricole melun nord", "123 albert Street", "77000", "Melun");

        //when
        Bank bank = bankMapper.toEntity(bankdto);

        //then
        assertThat(bank).isNotNull();
        assertThat(bank.getName()).isEqualTo("Crédit agricole melun nord");
        assertThat(bank.getAddress()).isEqualTo("123 albert Street");
        assertThat(bank.getZip()).isEqualTo("77000");
        assertThat(bank.getCity()).isEqualTo("Melun");
    }

}
