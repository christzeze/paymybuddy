package com.openclassroooms.paymybuddy.controller;

import com.openclassroooms.paymybuddy.dto.BankDto;
import com.openclassroooms.paymybuddy.dto.ContactDto;
import com.openclassroooms.paymybuddy.mapper.ContactMapper;
import com.openclassroooms.paymybuddy.service.BankService;
import com.openclassroooms.paymybuddy.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/contact")

public class ContactController {

    public ContactService contactService;

    public ContactController(ContactService contactService) {
        super();
        this.contactService = contactService;
    }

    @ModelAttribute("contact")
    public ContactDto contactDto() {
        return new ContactDto();
    }

    @GetMapping
    public String showContactForm() {
        return "contact";
    }

    @PostMapping
    public String registerContact(@ModelAttribute("contact") ContactDto contactDto) {
        contactService.createContact(contactDto);
        return "redirect:/transfer";
    }
}
