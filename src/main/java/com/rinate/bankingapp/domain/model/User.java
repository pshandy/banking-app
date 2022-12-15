package com.rinate.bankingapp.domain.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
@Data
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(columnDefinition="TEXT", nullable = false, unique = true)
    private String email;

    @Column(columnDefinition="TEXT", nullable = false)
    private String firstname;

    @Column(columnDefinition="TEXT", nullable = false)
    private String lastname;

    @Column(columnDefinition="TEXT", nullable = true)
    private String middleName;

    @Column(columnDefinition="TEXT", nullable = false)
    private String phone;

    @Column(columnDefinition="TEXT", nullable = false, unique = true)
    private String username;

    @Column(columnDefinition="TEXT", nullable = false)
    private String password;

    @Column(columnDefinition="TEXT", nullable = false)
    private String role;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Appointment> appointmentList;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Account> accountList;

    @OneToMany(mappedBy = "creator", cascade = CascadeType.ALL)
    private List<Bill> creatorsBills;

    @OneToMany(mappedBy = "recipient", cascade = CascadeType.ALL)
    private List<Bill> recipientsBills;



}
