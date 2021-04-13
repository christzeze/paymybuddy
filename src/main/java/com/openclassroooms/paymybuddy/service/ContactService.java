package com.openclassroooms.paymybuddy.service;

import com.openclassroooms.paymybuddy.dto.ContactDto;
import com.openclassroooms.paymybuddy.model.Contact;
import com.openclassroooms.paymybuddy.model.User;

import java.util.List;

public interface ContactService {
    public Contact createContact(Contact contact);
    public void updateContact(Contact contact);
    public List<Contact> ListOfContacts(User user);
}

