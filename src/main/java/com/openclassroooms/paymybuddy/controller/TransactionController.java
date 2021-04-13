package com.openclassroooms.paymybuddy.controller;

import com.openclassroooms.paymybuddy.dto.TransactionDto;
import com.openclassroooms.paymybuddy.mapper.TransactionMapper;
import com.openclassroooms.paymybuddy.model.*;
import com.openclassroooms.paymybuddy.repository.*;
import com.openclassroooms.paymybuddy.service.AccountService;
import com.openclassroooms.paymybuddy.service.TransactionService;
import com.openclassroooms.paymybuddy.service.UserService;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/transaction")

public class TransactionController {

    @Autowired
    private TransactionRepository transactionRepository;


    private TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    private final TransactionMapper transactionMapper = Mappers.getMapper(TransactionMapper.class);

    @Autowired
    private UserService userService;

    @Autowired
    private AccountService accountService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ContactRepository contactRepository;

    @Autowired
    private AccountRepository accountRepository;

    @GetMapping
    public String showTransferForm(@ModelAttribute TransactionDto transactionDto, BindingResult bindingResult, HttpServletRequest request, Model model) {
        if (bindingResult.hasErrors()) {
            //errors processing
        }
        int page = 0; //default page number is 0 (yes it is weird)
        int size = 5; //default page size is 10
        int pageSize = 5;
        int pageNo = 0;

        if (request.getParameter("page") != null && !request.getParameter("page").isEmpty()) {
            pageNo = Integer.parseInt(request.getParameter("page")) - 1;
        }

        if (request.getParameter("size") != null && !request.getParameter("size").isEmpty()) {
            pageSize = Integer.parseInt(request.getParameter("size"));
        }


        // find user for definition of emitter
        User user=userService.findUser();
        model.addAttribute("emitter1",user);

        // List of accounts
        List<Account> accounts = accountService.listOfAccounts(user);
        model.addAttribute("accounts", accounts);

        // List of user accounts
        List<Account>userAccounts=accountService.listOfUserAccounts();
        model.addAttribute("useraccounts",userAccounts);

        // Pagination
        Page<Transaction> page1=transactionService.pagination(user,pageNo,pageSize);
        List<Transaction> listTransactions = page1.getContent();
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page1.getTotalPages());
        model.addAttribute("totalItems", page1.getTotalElements());
        model.addAttribute("transactions", listTransactions);
        model.addAttribute("transaction", new TransactionDto());
        return "transaction";

    }

    @PostMapping
    public String registerUserAccount(@ModelAttribute("transaction") TransactionDto transactionDto) {
        transactionService.createTransaction(transactionMapper.toEntity(transactionDto));
        return "redirect:/transaction";
    }
}

