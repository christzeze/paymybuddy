package com.openclassroooms.paymybuddy.controller;

import com.openclassroooms.paymybuddy.dto.BankDto;
import com.openclassroooms.paymybuddy.dto.UserDto;
import com.openclassroooms.paymybuddy.mapper.UserMapper;
import com.openclassroooms.paymybuddy.model.Bank;
import com.openclassroooms.paymybuddy.model.User;
import com.openclassroooms.paymybuddy.repository.BankRepository;
import com.openclassroooms.paymybuddy.service.UserService;
import org.mapstruct.factory.Mappers;
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

    private final UserMapper userMapper = Mappers.getMapper( UserMapper.class );

    @ModelAttribute("user")
    public UserDto userRegistrationDto() {
        return new UserDto();
    }

    @GetMapping
    public String showRegistrationForm(Model model) {
        List<Bank> banks = bankRepository.findAll();
        model.addAttribute("banks", banks);
        model.addAttribute("user", new User());
        BankDto bank= userRegistrationDto().getBank();
        return "registration";
    }

    @PostMapping
    public String registerUserAccount(@ModelAttribute("user") UserDto registrationDto) {
        userService.save(userMapper.toEntity(registrationDto));
        return "redirect:/login";
    }

}
