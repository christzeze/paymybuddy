package com.openclassroooms.paymybuddy.controller;

import com.openclassroooms.paymybuddy.dto.UserRegistrationDto;
import com.openclassroooms.paymybuddy.model.Bank;
import com.openclassroooms.paymybuddy.repository.BankRepository;
import com.openclassroooms.paymybuddy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/registration")
public class UserRegistrationController {
    private UserService userService;

    @Autowired
    private BankRepository bankRepository;

    public UserRegistrationController(UserService userService) {
        super();
        this.userService = userService;
    }

    @ModelAttribute("user")
    public UserRegistrationDto userRegistrationDto() {
        return new UserRegistrationDto();
    }

    @GetMapping
    public String showRegistrationForm(Model model) {
        Iterable<Bank> banks = bankRepository.findAll();
        model.addAttribute("banks", banks);
        return "registration2";
    }

    @PostMapping
    public String registerUserAccount(@ModelAttribute("user") UserRegistrationDto registrationDto) {
        userService.save(registrationDto);
        return "redirect:/login";
    }

}
