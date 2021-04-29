package com.openclassroooms.paymybuddy.controller;

import com.openclassroooms.paymybuddy.dto.UserDto;
import com.openclassroooms.paymybuddy.mapper.UserMapper;
import com.openclassroooms.paymybuddy.model.User;
import com.openclassroooms.paymybuddy.repository.UserRepository;
import com.openclassroooms.paymybuddy.service.UserService;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/profil")
public class ProfilController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    private final UserMapper userMapper = Mappers.getMapper(UserMapper.class);

    @ModelAttribute("profil")
    public UserDto userDto() {
        return new UserDto();
    }

    @GetMapping
    public String showProfilForm(Model model) {
        // find user for definition of emitter
        User user = userService.findUser();
        model.addAttribute("emitter", user);
        model.addAttribute("user", user);
        return "profil";
    }

    @PostMapping
    public String registerUser(@ModelAttribute("profil") UserDto userDto) {
        userService.save(userMapper.toEntity(userDto));
        return "redirect:/profil";
    }
}
