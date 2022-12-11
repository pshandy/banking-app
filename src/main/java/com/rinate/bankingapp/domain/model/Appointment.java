package com.rinate.bankingapp.domain.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private Date date;

    private String location;

    private String description;

    private Boolean confirmed;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}
