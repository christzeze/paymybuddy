package com.openclassroooms.paymybuddy.controller;

import com.openclassroooms.paymybuddy.dto.AccountDto;
import com.openclassroooms.paymybuddy.mapper.AccountMapper;
import com.openclassroooms.paymybuddy.model.Bank;
import com.openclassroooms.paymybuddy.model.User;
import com.openclassroooms.paymybuddy.service.AccountService;
import com.openclassroooms.paymybuddy.service.BankService;
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
@RequestMapping("/contact")
public class ContactController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private UserService userService;

    @Autowired
    private BankService bankService;

    private final AccountMapper accountMapper = Mappers.getMapper(AccountMapper.class);

    @ModelAttribute("contact")
    public AccountDto accountDto() {
        return new AccountDto();
    }

    @GetMapping
    public String showContactForm(Model model) {
        List<Bank> banks = bankService.findAll();
        model.addAttribute("banks", banks);
        model.addAttribute("bank", new Bank());

        // find user for definition of emitter
        User user = userService.findUser();
        model.addAttribute("emitter", user);

        return "contact";
    }

    @PostMapping
    public String registerContact(@ModelAttribute("contact") AccountDto accountDto) {
        accountDto.setUserAccount(false);
        accountService.createAccount(accountMapper.toEntity(accountDto));
        return "redirect:/transaction";
    }
}
