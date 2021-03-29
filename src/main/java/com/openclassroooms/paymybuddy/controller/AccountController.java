package com.openclassroooms.paymybuddy.controller;

import com.openclassroooms.paymybuddy.dto.AccountDto;
import com.openclassroooms.paymybuddy.mapper.AccountMapper;
import com.openclassroooms.paymybuddy.model.Account;
import com.openclassroooms.paymybuddy.model.Bank;
import com.openclassroooms.paymybuddy.model.User;
import com.openclassroooms.paymybuddy.repository.BankRepository;
import com.openclassroooms.paymybuddy.repository.UserRepository;
import com.openclassroooms.paymybuddy.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/account")

public class AccountController {
    private AccountService accountService;
    @Autowired
    private BankRepository bankRepository;
    @Autowired
    private UserRepository userRepository;

    private Account account;

    public AccountController(AccountService accountService) {
        super();
        this.accountService = accountService;
    }

    @ModelAttribute("account")
    public AccountDto accountDto() {
        return new AccountDto();
    }

    @GetMapping
    public String showRegistrationAccountForm(Model model) {
        Iterable<Bank> banks = bankRepository.findAll();
        model.addAttribute("banks", banks);
        model.addAttribute("bank",new Bank());
        return "account";
    }

    @PostMapping
    public String registerUserAccount(@ModelAttribute("account")AccountDto accountDto) {
        //Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        //String name = auth.getName();
        //User user=userRepository.findByEmail(name);
        //Bank bank=bankRepository.findByName(accountDto.getBank().getName());
        //accountService.createAccount(accountDto,user,bank);
        accountService.createAccount(accountDto);
        return "redirect:/home";
    }
}
