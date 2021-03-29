package com.openclassroooms.paymybuddy.mapper;

import com.openclassroooms.paymybuddy.dto.ContactDto;
import com.openclassroooms.paymybuddy.model.Contact;
import org.mapstruct.Mapper;

@Mapper(uses = UserMapper.class)
public interface ContactMapper extends AbstractMapper<Contact, ContactDto> {
}
