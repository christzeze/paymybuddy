package com.openclassroooms.paymybuddy.service;

import com.openclassroooms.paymybuddy.model.Contact;
import com.openclassroooms.paymybuddy.model.User;

import java.util.List;

public interface ContactService {
    Contact createContact(Contact contact) throws Exception;

    List<Contact> ListOfContacts(User user);
}

