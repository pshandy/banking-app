package com.rinate.bankingapp.web.controller.admin;

import com.rinate.bankingapp.domain.model.Transaction;
import com.rinate.bankingapp.domain.repository.AccountRepository;
import com.rinate.bankingapp.domain.repository.TransactionRepository;
import com.rinate.bankingapp.domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class TransactionController {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private AccountRepository accountRepository;

    @GetMapping("/admin/transaction")
    public String showTransaction(Model model) {
        model.addAttribute("transaction", new Transaction());
        model.addAttribute("transactions", transactionRepository.findAll());
        model.addAttribute("accounts", accountRepository.findAll());
        return "/admin/panel/TransactionPanel";
    }

    @GetMapping("/admin/transaction/{id}")
    public String showEditTransaction(Model model, @PathVariable("id") Integer id) {
        model.addAttribute("transaction", transactionRepository.findById(id).get());
        model.addAttribute("transactions", transactionRepository.findAll());
        model.addAttribute("accounts", accountRepository.findAll());
        return "/admin/panel/TransactionPanel";
    }

    @PostMapping("/admin/transaction")
    public ModelAndView saveTransaction(RedirectAttributes redirectAttributes, @ModelAttribute("transaction") Transaction transaction) {
        try {
            transactionRepository.save(transaction);
        } catch (RuntimeException ex) {
            redirectAttributes.addFlashAttribute("errorMessage", "Не удалось сохранить запись");
            redirectAttributes.addFlashAttribute("transaction", transaction);
            return new ModelAndView("redirect:/admin/transaction");
        }
        redirectAttributes.addFlashAttribute("successMessage", "Сохранено");
        return new ModelAndView("redirect:/admin/transaction");
    }

    @DeleteMapping(path = "/admin/transaction/{id}")
    public ModelAndView deleteTransaction(RedirectAttributes redirectAttributes, @PathVariable("id") Integer id) {
        Transaction transaction = transactionRepository.findById(id).get();
        try {
            transactionRepository.delete(transaction);
        } catch (RuntimeException ex) {
            redirectAttributes.addFlashAttribute("errorMessage", "Не удалось удалить запись");
            redirectAttributes.addFlashAttribute("transaction", transaction);
            return new ModelAndView("redirect:/admin/transaction");
        }
        redirectAttributes.addFlashAttribute("successMessage", "Удалено");
        return new ModelAndView("redirect:/admin/transaction");
    }

}
