package com.rinate.bankingapp.service;

import com.rinate.bankingapp.domain.model.Appointment;
import com.rinate.bankingapp.domain.repository.AppointmentDao;
import org.springframework.stereotype.Service;

@Service
public class AppointmentServiceImpl implements AppointmentService {

    private final AppointmentDao appointmentDao;

    public AppointmentServiceImpl(AppointmentDao appointmentDao) {
        this.appointmentDao = appointmentDao;
    }

    public void confirmAppointment(Integer id) {
        Appointment appointment = appointmentDao.findById(id).get();
        appointment.setConfirmed(true);
        appointmentDao.save(appointment);
    }

    public Appointment createAppointment(Appointment appointment) {
        return appointmentDao.save(appointment);
    }

}
