package com.openclassroooms.paymybuddy.mapperTest;

import com.openclassroooms.paymybuddy.dto.AccountDto;
import com.openclassroooms.paymybuddy.dto.BankDto;
import com.openclassroooms.paymybuddy.dto.ContactDto;
import com.openclassroooms.paymybuddy.dto.UserDto;
import com.openclassroooms.paymybuddy.mapper.AccountMapper;
import com.openclassroooms.paymybuddy.mapper.ContactMapper;
import com.openclassroooms.paymybuddy.model.Account;
import com.openclassroooms.paymybuddy.model.Bank;
import com.openclassroooms.paymybuddy.model.Contact;
import com.openclassroooms.paymybuddy.model.User;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class ContactMapperTest {
    private final ContactMapper contactMapper = Mappers.getMapper(ContactMapper.class);

    @Test
    public void shouldMapContactToDto() {
        //given
        User user = new User(2,"jacques", "martin", "jacquesmartin@gmail.com", "123456");
        Bank bank = new Bank("Crédit agricole melun nord", "123 albert Street", "77000", "Melun");
        Contact contact = new Contact(1, "doe", "johndoe@gmail.com", "bb123456789", user, bank);

        //when
        ContactDto contactDto = contactMapper.toDTO(contact);

        //then
        assertThat(contactDto).isNotNull();
        assertThat(contactDto.getBank().getName()).isEqualTo("Crédit agricole melun nord");
        assertThat(contactDto.getIban()).isEqualTo("bb123456789");
        assertThat(contactDto.getLastName()).isEqualTo("doe");
        assertThat(contactDto.getUser().getLastName()).isEqualTo("martin");
        assertThat(contactDto.getEmail()).isEqualTo("johndoe@gmail.com");


    }

    @Test
    public void shouldMapContactToEntity() {
        //given
        UserDto userDto = new UserDto(2,"jacques", "martin", "jacquesmartin@gmail.com", "123456");
        BankDto bankDto = new BankDto(1,"Crédit agricole melun nord", "123 albert Street", "77000", "Melun");
        ContactDto contactDto = new ContactDto(1, "doe", "johndoe@gmail.com", "bb123456789", userDto, bankDto);
        //when
        Contact contact=contactMapper.toEntity(contactDto);

        //then
        assertThat(contact).isNotNull();
        assertThat(contact.getBank().getName()).isEqualTo("Crédit agricole melun nord");
        assertThat(contact.getIban()).isEqualTo("bb123456789");
        assertThat(contact.getLastName()).isEqualTo("doe");
        assertThat(contact.getUser().getLastName()).isEqualTo("martin");
        assertThat(contact.getEmail()).isEqualTo("johndoe@gmail.com");
    }
}
