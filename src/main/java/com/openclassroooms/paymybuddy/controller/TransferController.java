package com.openclassroooms.paymybuddy.controller;

import com.openclassroooms.paymybuddy.dto.TransferDto;
import com.openclassroooms.paymybuddy.dto.UserRegistrationDto;
import com.openclassroooms.paymybuddy.model.Bank;
import com.openclassroooms.paymybuddy.model.Transaction;
import com.openclassroooms.paymybuddy.repository.BankRepository;
import com.openclassroooms.paymybuddy.repository.TransactionRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/transfer")

public class TransferController {

    private TransactionRepository transactionRepository;

    @GetMapping
    public String showTransferForm(@ModelAttribute TransferDto transferDto, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            //errors processing
        }
        model.addAttribute("transfer", transferDto);
        return "transfer";
        //return "transfer";
    }

    @PostMapping
    public String registerUserAccount(@ModelAttribute("transfer") TransferDto transferDto) {
        //Transaction transaction=new Transaction();
        //Transaction senderEmail=transactionRepository.findAccountByUserEmail(transferDto.getSenderEmail());
        //Transaction receiverEmail=transactionRepository.findAccountByUserEmail(transferDto.getReceiverEmail());

        return "redirect:/home";
    }
}

