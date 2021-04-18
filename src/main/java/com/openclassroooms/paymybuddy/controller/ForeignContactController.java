package com.openclassroooms.paymybuddy.controller;

import com.openclassroooms.paymybuddy.dto.ContactDto;
import com.openclassroooms.paymybuddy.mapper.ContactMapper;
import com.openclassroooms.paymybuddy.model.Bank;
import com.openclassroooms.paymybuddy.model.User;
import com.openclassroooms.paymybuddy.repository.UserRepository;
import com.openclassroooms.paymybuddy.service.BankService;
import com.openclassroooms.paymybuddy.service.ContactService;
import com.openclassroooms.paymybuddy.service.UserService;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/foreigncontact")
public class ForeignContactController {
    public ContactService contactService;
    private final ContactMapper contactMapper = Mappers.getMapper(ContactMapper.class);

    public ForeignContactController(ContactService contactService) {
        this.contactService = contactService;
    }

    @Autowired
    private BankService bankService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;


    @ModelAttribute("foreigncontact")
    public ContactDto contactDto() {
        return new ContactDto();
    }

    @GetMapping
    public String showContactForm(Model model) {

        List<Bank> banks = bankService.findAll();
        model.addAttribute("banks", banks);
        model.addAttribute("bank", new Bank());

        // find user for definition of emitter
        User user = userService.findUser();
        model.addAttribute("emitter", user);

        return "foreigncontact";
    }

    @PostMapping
    public String registerContact(@ModelAttribute("contact") ContactDto contactDto) throws Exception {
        contactService.createContact(contactMapper.toEntity(contactDto));
        return "redirect:/foreigntransaction";
    }
}
