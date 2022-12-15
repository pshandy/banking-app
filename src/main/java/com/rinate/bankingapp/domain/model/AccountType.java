package com.rinate.bankingapp.domain.model;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.stereotype.Controller;

import java.sql.Date;
import java.util.List;

@Data
@Entity
public class AccountType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(columnDefinition="TEXT", nullable = false, unique = true)
    private String name;

    @OneToMany(mappedBy = "accountType")
    private List<Account> accounts;

}
