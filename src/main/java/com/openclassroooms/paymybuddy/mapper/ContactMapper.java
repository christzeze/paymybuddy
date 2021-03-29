package com.openclassroooms.paymybuddy.mapper;

import com.openclassroooms.paymybuddy.dto.BankDto;
import com.openclassroooms.paymybuddy.dto.ContactDto;
import com.openclassroooms.paymybuddy.model.Bank;
import com.openclassroooms.paymybuddy.model.Contact;
import org.springframework.beans.factory.annotation.Autowired;

public class ContactMapper extends AbstratMapper<Contact, ContactDto>{

    @Autowired
    public UserMapper userMapper;

    @Override
    public Contact toEntity(ContactDto contactDto) {
        if(contactDto==null) {
            return null;
        }
        Contact contactToEntity=new Contact();
        contactToEntity.setEmail(contactDto.getEmail());
        contactToEntity.setId(contactDto.getId());
        contactToEntity.setLastName(contactDto.getLastName());
        contactToEntity.setIban(contactDto.getIban());
        contactToEntity.setUser(userMapper.toEntity(contactDto.getUserDto()));
        return contactToEntity;
    }

    public ContactDto toDTO(Contact contact) {
        if (contact==null) {
            return null;
        }
        ContactDto contactDto =new ContactDto();
        contactDto.setId(contact.getId());
        contactDto.setLastName(contact.getLastName());
        contactDto.setEmail(contact.getEmail());
        contactDto.setIban(contact.getIban());
        contactDto.setUserDto(userMapper.toDTO(contact.getUser()));
        return contactDto;
    }

}
