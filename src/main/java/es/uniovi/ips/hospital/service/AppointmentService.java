package es.uniovi.ips.hospital.service;

import es.uniovi.ips.hospital.domain.Appointment;
import es.uniovi.ips.hospital.domain.Doctor;
import es.uniovi.ips.hospital.exception.BusinessException;
import es.uniovi.ips.hospital.repository.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("AppointmentService")
public class AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    public Appointment createAppointment(Appointment appointment) throws BusinessException {
        if (appointmentRepository.findByPatientAndStartTime(appointment.getPatient(), appointment.getStartTime()) != null)
            throw new BusinessException("This appointment already exists");
        appointmentRepository.save(appointment);
        return appointmentRepository.findByPatientAndStartTime(appointment.getPatient(), appointment.getStartTime());
    }

    public List<Appointment> findAllByDoctor(Doctor myself) {
        return appointmentRepository.findAllByDoctors(myself);
    }

    public void updateAppointment(Appointment appointment) {
        appointmentRepository.save(appointment);
    }

    public List<Appointment> findAllAppointments() {
        return appointmentRepository.findAll();
    }
}
