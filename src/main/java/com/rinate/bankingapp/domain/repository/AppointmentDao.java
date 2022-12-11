package com.rinate.bankingapp.domain.repository;

import com.rinate.bankingapp.domain.model.Appointment;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AppointmentDao extends CrudRepository<Appointment, Integer> {
    List<Appointment> findAll();
}
