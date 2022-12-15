package com.rinate.bankingapp.domain.repository;

import com.rinate.bankingapp.domain.model.Transaction;
import org.springframework.data.repository.CrudRepository;

public interface TransactionRepository extends CrudRepository<Transaction, Integer> {
}
