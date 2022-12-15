package com.rinate.bankingapp.domain.repository;

import com.rinate.bankingapp.domain.model.Bill;
import org.springframework.data.repository.CrudRepository;

public interface BillRepository extends CrudRepository<Bill, Integer> {
}
