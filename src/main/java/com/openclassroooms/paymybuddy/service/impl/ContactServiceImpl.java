package com.openclassroooms.paymybuddy.service.impl;

import com.openclassroooms.paymybuddy.model.Contact;
import com.openclassroooms.paymybuddy.model.User;
import com.openclassroooms.paymybuddy.repository.ContactRepository;
import com.openclassroooms.paymybuddy.repository.UserRepository;
import com.openclassroooms.paymybuddy.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ContactServiceImpl implements ContactService {
    @Autowired
    private ContactRepository contactRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<Contact> ListOfContacts(User user) {
        return contactRepository.findByuserId(user.getId());
    }

    @Override
    public Contact createContact(Contact contact) throws Exception {
        Contact contactMail = contactRepository.findByEmail(contact.getEmail());
        if (contactMail == null) {
            return contactRepository.save(contact);
        } else {
            throw new Exception("This email exist in database");
        }
    }


}
