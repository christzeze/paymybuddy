package com.openclassroooms.paymybuddy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/contactus")
public class ContactusController {

    @GetMapping("/contactus")
    public String contactus() {
        return "contactus";
    }

    @GetMapping
    public String showContactusForm(Model model) {
        return "contactus";
    }
}
