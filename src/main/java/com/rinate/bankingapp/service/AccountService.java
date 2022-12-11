package com.rinate.bankingapp.service;



import com.rinate.bankingapp.domain.model.PrimaryAccount;
import com.rinate.bankingapp.domain.model.SavingsAccount;

import java.security.Principal;

public interface AccountService {
	PrimaryAccount createPrimaryAccount();
    SavingsAccount createSavingsAccount();
    void deposit(String accountType, double amount, Principal principal);
    void withdraw(String accountType, double amount, Principal principal);
}
