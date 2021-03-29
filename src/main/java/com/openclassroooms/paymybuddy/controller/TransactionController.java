package com.openclassroooms.paymybuddy.controller;

import com.openclassroooms.paymybuddy.dto.TransactionDto;
import com.openclassroooms.paymybuddy.dto.TransferDto;
import com.openclassroooms.paymybuddy.model.Contact;
import com.openclassroooms.paymybuddy.repository.ContactRepository;
import com.openclassroooms.paymybuddy.repository.TransactionRepository;
import com.openclassroooms.paymybuddy.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/transaction")

public class TransactionController {

    private TransactionRepository transactionRepository;

    private TransactionService transactionService;

    @Autowired
    private ContactRepository contactRepository;

    @GetMapping
    public String showTransferForm(@ModelAttribute TransactionDto transactionDto, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            //errors processing
        }
        Iterable<Contact> contacts = contactRepository.findAll();
        model.addAttribute("contacts", contacts);

        model.addAttribute("transaction", transactionDto);

        return "transaction";

    }

    @PostMapping
    public String registerUserAccount(@ModelAttribute("transaction") TransactionDto transactionDto) {
        transactionService.CreateTransaction(transactionDto);
        return "redirect:/home";
    }
}

