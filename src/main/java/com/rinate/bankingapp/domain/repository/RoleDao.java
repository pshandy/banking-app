package com.rinate.bankingapp.domain.repository;


import com.rinate.bankingapp.domain.model.Role;
import org.springframework.data.repository.CrudRepository;

public interface RoleDao extends CrudRepository<Role, Integer> {
    Role findByName(String name);
}
