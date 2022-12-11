package com.rinate.bankingapp.domain.repository;

import com.rinate.bankingapp.domain.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserDao extends CrudRepository<User, Integer> {
	User findByUsername(String username);
    User findByEmail(String email);
    List<User> findAll();
}
