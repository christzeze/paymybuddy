package com.openclassroooms.paymybuddy.controller;


import com.openclassroooms.paymybuddy.dto.ForeignTransactionDto;
import com.openclassroooms.paymybuddy.dto.TransactionDto;
import com.openclassroooms.paymybuddy.mapper.ForeignTransactionMapper;
import com.openclassroooms.paymybuddy.mapper.TransactionMapper;
import com.openclassroooms.paymybuddy.model.Account;
import com.openclassroooms.paymybuddy.model.Contact;
import com.openclassroooms.paymybuddy.model.ForeignTransaction;
import com.openclassroooms.paymybuddy.model.User;
import com.openclassroooms.paymybuddy.repository.*;
import com.openclassroooms.paymybuddy.service.ForeignTransactionService;
import com.openclassroooms.paymybuddy.service.TransactionService;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/foreigntransaction")

public class ForeignTransactionController {
    @Autowired
    private ForeignTransactionRepository foreignTransactionRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AccountRepository accountRepository;

    private ForeignTransactionService foreignTransactionService;

    public ForeignTransactionController(ForeignTransactionService foreignTransactionService) {
        this.foreignTransactionService = foreignTransactionService;
    }

    private final ForeignTransactionMapper foreignTransactionMapper = Mappers.getMapper( ForeignTransactionMapper.class );


    @Autowired
    private ContactRepository contactRepository;

    @GetMapping
    public String showTransferForm(@ModelAttribute TransactionDto transactionDto, BindingResult bindingResult, HttpServletRequest request, Model model) {
        if (bindingResult.hasErrors()) {
            //errors processing
        }
        int page = 0; //default page number is 0 (yes it is weird)
        int size = 10; //default page size is 10
        int pageSize=5;
        int pageNo=0;

        if (request.getParameter("page") != null && !request.getParameter("page").isEmpty()) {
            pageNo = Integer.parseInt(request.getParameter("page")) - 1;
        }

        if (request.getParameter("size") != null && !request.getParameter("size").isEmpty()) {
            pageSize = Integer.parseInt(request.getParameter("size"));
        }


        List<Contact> contacts=contactRepository.findAll();
        model.addAttribute("contacts", contacts);

        String userMail= SecurityContextHolder.getContext().getAuthentication().getName();
        User user=userRepository.findByEmail(userMail);
        Account account=accountRepository.findById(user.getId());

        Page<ForeignTransaction> page1=foreignTransactionRepository.findBySender(account,PageRequest.of(pageNo, pageSize));
        List < ForeignTransaction > listForeignTransactions = page1.getContent();
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page1.getTotalPages());
        model.addAttribute("totalItems", page1.getTotalElements());
        model.addAttribute("foreigntransactions", listForeignTransactions);

        //model.addAttribute("foreigntransactions", foreignTransactionRepository.findBySender(account,PageRequest.of(page, size)));

        model.addAttribute("foreigntransaction", new ForeignTransactionDto());
        return "foreigntransaction";

    }

    @PostMapping
    public String registerUserAccount(@ModelAttribute("transaction") ForeignTransactionDto foreignTransactionDto) {
        foreignTransactionService.CreateTransaction(foreignTransactionMapper.toEntity(foreignTransactionDto));
        return "redirect:/home";
    }
}
