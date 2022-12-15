package com.rinate.bankingapp.domain.repository;

import com.rinate.bankingapp.domain.model.Account;
import org.springframework.data.repository.CrudRepository;

public interface AccountRepository extends CrudRepository<Account, Integer> {
}
