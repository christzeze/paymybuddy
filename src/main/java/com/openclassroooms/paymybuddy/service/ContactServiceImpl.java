package com.openclassroooms.paymybuddy.service;

import com.openclassroooms.paymybuddy.dto.ContactDto;
import com.openclassroooms.paymybuddy.model.Contact;
import com.openclassroooms.paymybuddy.model.User;
import com.openclassroooms.paymybuddy.repository.ContactRepository;
import com.openclassroooms.paymybuddy.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class ContactServiceImpl implements ContactService{

    private ContactRepository contactRepository;

    @Autowired
    private UserRepository userRepository;

    public ContactServiceImpl(ContactRepository contactRepository) {
        super();
        this.contactRepository = contactRepository;
    }

    @Override
    public Contact createContact(ContactDto contactDto) {
        Contact contact=new Contact(contactDto.getLastName(), contactDto.getEmail(), contactDto.getIban());
        String userMail=SecurityContextHolder.getContext().getAuthentication().getName();
        User aaUser = userRepository.findByEmail(userMail);
        contact.setAaUser(aaUser);
        Contact contactSaved=contactRepository.save(contact);
        return contactSaved;
    }

    @Override
    public void updateContact(Contact contact) {

    }
}
