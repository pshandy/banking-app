package com.rinate.bankingapp.domain.repository;


import com.rinate.bankingapp.domain.model.Recipient;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RecipientDao extends CrudRepository<Recipient, Integer> {
    List<Recipient> findAll();
    Recipient findByName(String recipientName);
    void deleteByName(String recipientName);
}
