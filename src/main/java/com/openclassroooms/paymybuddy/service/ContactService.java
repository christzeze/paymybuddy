package com.openclassroooms.paymybuddy.service;

import com.openclassroooms.paymybuddy.dto.BankDto;
import com.openclassroooms.paymybuddy.dto.ContactDto;
import com.openclassroooms.paymybuddy.model.Bank;
import com.openclassroooms.paymybuddy.model.Contact;

public interface ContactService {
    public Contact createContact(ContactDto contactDto);
    public void updateContact(Contact contact);
}
