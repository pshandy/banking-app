package com.rinate.bankingapp.domain.repository;

import com.rinate.bankingapp.domain.model.PrimaryAccount;
import org.springframework.data.repository.CrudRepository;

public interface PrimaryAccountDao extends CrudRepository<PrimaryAccount, Integer> {
    PrimaryAccount findByAccountNumber (int accountNumber);
}
