package com.rinate.bankingapp.domain.repository;

import com.rinate.bankingapp.domain.model.Appointment;
import org.springframework.data.repository.CrudRepository;

public interface AppointmentRepository extends CrudRepository<Appointment, Integer> {
}
