package com.rinate.bankingapp.web.controller;

import com.rinate.bankingapp.domain.model.User;
import com.rinate.bankingapp.domain.repository.TransactionRepository;
import com.rinate.bankingapp.domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.HashSet;
import java.util.Set;

@Controller
public class HomeController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    @GetMapping("/")
    public String home() {
        return "redirect:/index";
    }

    @GetMapping("/index")
    public String index() {
        return "index";
    }

    @GetMapping(value = "/signup")
    public String signup(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "signup";
    }

    @PostMapping(value = "/signup")
    public String signupPost(@ModelAttribute("user") User user, Model model) {
        if (userRepository.existsByEmail(user.getEmail()) || userRepository.existsByUsername(user.getUsername()))  {
            if (userRepository.existsByEmail(user.getEmail())) {
                model.addAttribute("emailExists", true);
            }
            if (userRepository.existsByUsername(user.getUsername())) {
                model.addAttribute("usernameExists", true);
            }
            return "signup";
        } else {
            user.setRole("ROLE_USER");
            userRepository.save(user);
            return "redirect:/";
        }
    }

    @RequestMapping("/home")
    public String userFront(Principal principal, Model model) {
        User user = userRepository.findByUsername(principal.getName());
        model.addAttribute("accounts", user.getAccountList());
        model.addAttribute("transactions", transactionRepository.findAll());
        return "homepage";
    }

}
