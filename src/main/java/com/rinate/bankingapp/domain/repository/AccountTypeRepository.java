package com.rinate.bankingapp.domain.repository;

import com.rinate.bankingapp.domain.model.AccountType;
import org.springframework.data.repository.CrudRepository;

public interface AccountTypeRepository extends CrudRepository<AccountType, Integer> {
}
