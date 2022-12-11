package com.rinate.bankingapp.service;


import com.rinate.bankingapp.domain.model.*;
import com.rinate.bankingapp.domain.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.security.Principal;
import java.util.Date;

@Service
public class AccountServiceImpl implements AccountService {
	
	private static int nextAccountNumber = 11223145;

    private final PrimaryAccountDao primaryAccountDao;

    private final SavingsAccountDao savingsAccountDao;

    private final UserDao userDao;

    private final PrimaryTransactionDao primaryTransactionDao;

    private final SavingsTransactionDao savingsTransactionDao;

    public AccountServiceImpl (
            PrimaryAccountDao primaryAccountDao,
            SavingsAccountDao savingsAccountDao,
            UserDao userDao,
            PrimaryTransactionDao primaryTransactionDao,
            SavingsTransactionDao savingsTransactionDao
    ) {
        this.primaryAccountDao = primaryAccountDao;
        this.savingsAccountDao = savingsAccountDao;
        this.userDao = userDao;
        this.primaryTransactionDao = primaryTransactionDao;
        this.savingsTransactionDao = savingsTransactionDao;
    }

    public PrimaryAccount createPrimaryAccount() {
        PrimaryAccount primaryAccount = new PrimaryAccount();
        primaryAccount.setAccountBalance(new BigDecimal(0.0));
        primaryAccount.setAccountNumber(accountGen());
        primaryAccountDao.save(primaryAccount);
        return primaryAccountDao.findByAccountNumber(primaryAccount.getAccountNumber());
    }

    public SavingsAccount createSavingsAccount() {
        SavingsAccount savingsAccount = new SavingsAccount();
        savingsAccount.setAccountBalance(new BigDecimal(0.0));
        savingsAccount.setAccountNumber(accountGen());
        savingsAccountDao.save(savingsAccount);
        return savingsAccountDao.findByAccountNumber(savingsAccount.getAccountNumber());
    }
    
    public void deposit(String accountType, double amount, Principal principal) {

        User user = userDao.findByUsername(principal.getName());

        if (accountType.equalsIgnoreCase("Primary")) {

            PrimaryAccount primaryAccount = user.getPrimaryAccount();
            primaryAccount.setAccountBalance(primaryAccount.getAccountBalance().add(new BigDecimal(amount)));
            primaryAccountDao.save(primaryAccount);
            Date date = new Date();
            PrimaryTransaction primaryTransaction = new PrimaryTransaction(date, "Deposit to Primary Account", "Account", "Finished", amount, primaryAccount.getAccountBalance(), primaryAccount);
            primaryTransactionDao.save(primaryTransaction);

        } else if (accountType.equalsIgnoreCase("Savings")) {

            SavingsAccount savingsAccount = user.getSavingsAccount();
            savingsAccount.setAccountBalance(savingsAccount.getAccountBalance().add(new BigDecimal(amount)));
            savingsAccountDao.save(savingsAccount);
            Date date = new Date();
            SavingsTransaction savingsTransaction = new SavingsTransaction(date, "Deposit to savings Account", "Account", "Finished", amount, savingsAccount.getAccountBalance(), savingsAccount);
            savingsTransactionDao.save(savingsTransaction);

        }
    }
    
    public void withdraw(String accountType, double amount, Principal principal) {
        User user = userDao.findByUsername(principal.getName());

        if (accountType.equalsIgnoreCase("Primary")) {

            PrimaryAccount primaryAccount = user.getPrimaryAccount();
            primaryAccount.setAccountBalance(primaryAccount.getAccountBalance().subtract(new BigDecimal(amount)));
            primaryAccountDao.save(primaryAccount);
            Date date = new Date();
            PrimaryTransaction primaryTransaction = new PrimaryTransaction(date, "Withdraw from Primary Account", "Account", "Finished", amount, primaryAccount.getAccountBalance(), primaryAccount);
            primaryTransactionDao.save(primaryTransaction);

        } else if (accountType.equalsIgnoreCase("Savings")) {

            SavingsAccount savingsAccount = user.getSavingsAccount();
            savingsAccount.setAccountBalance(savingsAccount.getAccountBalance().subtract(new BigDecimal(amount)));
            savingsAccountDao.save(savingsAccount);

            Date date = new Date();
            SavingsTransaction savingsTransaction = new SavingsTransaction(date, "Withdraw from savings Account", "Account", "Finished", amount, savingsAccount.getAccountBalance(), savingsAccount);
            savingsTransactionDao.save(savingsTransaction);

        }
    }
    
    private int accountGen() {
        return ++nextAccountNumber;
    }

	

}
