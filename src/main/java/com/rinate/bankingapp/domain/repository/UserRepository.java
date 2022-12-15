package com.rinate.bankingapp.domain.repository;

import com.rinate.bankingapp.domain.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {
}
