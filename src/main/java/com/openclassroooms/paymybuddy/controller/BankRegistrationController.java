package com.openclassroooms.paymybuddy.controller;

import com.openclassroooms.paymybuddy.dto.BankDto;
import com.openclassroooms.paymybuddy.mapper.BankMapper;
import com.openclassroooms.paymybuddy.service.BankService;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/bankregistration")

public class BankRegistrationController {

    private final BankService bankService;
    private final BankMapper bankMapper = Mappers.getMapper( BankMapper.class );


    public BankRegistrationController(BankService bankService) {
        super();
        this.bankService = bankService;
    }

    @ModelAttribute("bank")
    public BankDto bankDto() {
        return new BankDto();
    }

    @GetMapping
    public String showRegistrationBankForm() {
        return "bankregistration";
    }

    @PostMapping
    public String registerBank(@ModelAttribute("bank") BankDto bankDto) {
        bankService.createAccount(bankMapper.toEntity(bankDto));
        return "redirect:/home";
    }
}
