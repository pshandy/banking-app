package com.rinate.bankingapp.domain.repository;


import com.rinate.bankingapp.domain.model.SavingsAccount;
import org.springframework.data.repository.CrudRepository;

public interface SavingsAccountDao extends CrudRepository<SavingsAccount, Long> {
    SavingsAccount findByAccountNumber (int accountNumber);
}
