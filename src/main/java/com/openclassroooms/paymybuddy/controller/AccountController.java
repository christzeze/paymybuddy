package com.openclassroooms.paymybuddy.controller;

import com.openclassroooms.paymybuddy.dto.AccountDto;
import com.openclassroooms.paymybuddy.dto.BankDto;
import com.openclassroooms.paymybuddy.mapper.AccountMapper;
import com.openclassroooms.paymybuddy.model.Bank;
import com.openclassroooms.paymybuddy.repository.AccountRepository;
import com.openclassroooms.paymybuddy.service.AccountService;
import com.openclassroooms.paymybuddy.service.BankService;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/account")
public class AccountController {
    private final AccountService accountService;
    private final BankService bankService;
    private final AccountMapper accountMapper = Mappers.getMapper( AccountMapper.class );

    @Autowired
    private AccountRepository accountRepository;

    public AccountController(AccountService accountService, BankService bankService) {
        this.accountService = accountService;
        this.bankService = bankService;
    }

    @ModelAttribute("account")
    public AccountDto accountDto() {
        return new AccountDto();
    }



    @GetMapping()
    public String showRegistrationAccountForm(Model model) {
        List<Bank> banks = bankService.findAll();
        model.addAttribute("banks", banks);
        model.addAttribute("bank",new Bank());
        BankDto bank= accountDto().getBank();
        return "account";
    }

    @PostMapping
    public String registerUserAccount(@ModelAttribute("account") AccountDto accountDto) {
        accountService.createAccount(accountMapper.toEntity(accountDto));
        return "redirect:/home";
    }
}
