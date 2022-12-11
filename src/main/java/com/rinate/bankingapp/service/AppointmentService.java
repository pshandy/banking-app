package com.rinate.bankingapp.service;

import com.rinate.bankingapp.domain.model.Appointment;

public interface AppointmentService {
    void confirmAppointment(Integer id);

    Appointment createAppointment(Appointment appointment);

}
