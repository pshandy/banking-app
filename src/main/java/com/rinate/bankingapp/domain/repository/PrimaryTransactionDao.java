package com.rinate.bankingapp.domain.repository;

import com.rinate.bankingapp.domain.model.PrimaryTransaction;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PrimaryTransactionDao extends CrudRepository<PrimaryTransaction, Integer> {
    List<PrimaryTransaction> findAll();
}
