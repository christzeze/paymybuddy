package com.openclassroooms.paymybuddy.controller;


import com.openclassroooms.paymybuddy.dto.ForeignTransactionDto;
import com.openclassroooms.paymybuddy.dto.TransactionDto;
import com.openclassroooms.paymybuddy.mapper.ForeignTransactionMapper;
import com.openclassroooms.paymybuddy.model.Account;
import com.openclassroooms.paymybuddy.model.Contact;
import com.openclassroooms.paymybuddy.model.ForeignTransaction;
import com.openclassroooms.paymybuddy.model.User;
import com.openclassroooms.paymybuddy.service.AccountService;
import com.openclassroooms.paymybuddy.service.ContactService;
import com.openclassroooms.paymybuddy.service.ForeignTransactionService;
import com.openclassroooms.paymybuddy.service.UserService;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
@RequestMapping("/foreigntransaction")
public class ForeignTransactionController {

    @Autowired
    private ContactService contactService;

    @Autowired
    private AccountService accountService;

    private final ForeignTransactionService foreignTransactionService;

    @Autowired
    private UserService userService;

    public ForeignTransactionController(ForeignTransactionService foreignTransactionService) {
        this.foreignTransactionService = foreignTransactionService;
    }

    private final ForeignTransactionMapper foreignTransactionMapper = Mappers.getMapper(ForeignTransactionMapper.class);

    @GetMapping
    public String showTransferForm(@ModelAttribute TransactionDto transactionDto, BindingResult bindingResult, HttpServletRequest request, Model model) {
        int pageSize = 5;
        int pageNo = 0;

        if (request.getParameter("page") != null && !request.getParameter("page").isEmpty()) {
            pageNo = Integer.parseInt(request.getParameter("page")) - 1;
        }

        if (request.getParameter("size") != null && !request.getParameter("size").isEmpty()) {
            pageSize = Integer.parseInt(request.getParameter("size"));
        }

        // find user for definition of emitter
        User user = userService.findUser();
        model.addAttribute("emitter1", user);

        // List of contacts
        List<Contact> contacts = contactService.ListOfContacts(user);
        model.addAttribute("contacts", contacts);

        // List of user accounts
        List<Account> userAccounts = accountService.listOfUserAccounts();
        model.addAttribute("useraccounts", userAccounts);

        // Pagination
        Page<ForeignTransaction> page1 = foreignTransactionService.pagination(user, pageNo, pageSize);
        List<ForeignTransaction> listForeignTransactions = page1.getContent();
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page1.getTotalPages());
        model.addAttribute("totalItems", page1.getTotalElements());
        model.addAttribute("foreigntransactions", listForeignTransactions);
        model.addAttribute("foreigntransaction", new ForeignTransactionDto());
        return "foreigntransaction";

    }

    @PostMapping
    public String registerUserAccount(@ModelAttribute("transaction") ForeignTransactionDto foreignTransactionDto) throws Exception {
        foreignTransactionService.createTransaction(foreignTransactionMapper.toEntity(foreignTransactionDto));
        return "redirect:/foreigntransaction";
    }
}
