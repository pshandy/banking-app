package com.rinate.bankingapp.domain.repository;


import com.rinate.bankingapp.domain.model.SavingsTransaction;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SavingsTransactionDao extends CrudRepository<SavingsTransaction, Integer> {
    List<SavingsTransaction> findAll();
}

