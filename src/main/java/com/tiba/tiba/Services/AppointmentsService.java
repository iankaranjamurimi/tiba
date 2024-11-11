package com.tiba.tiba.Services;

import com.tiba.tiba.DTO.AppointmentsDTO;
import com.tiba.tiba.Entities.Appointments;
import com.tiba.tiba.Repositories.AppointmentsRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AppointmentsService {

    private final AppointmentsRepository appointmentsRepository;

    public Appointments createAppointment(AppointmentsDTO appointmentDTO) {
        Appointments appointment = new Appointments();
        // Map appointmentsDTO to appointment entity
        return saveAppointment(appointment);
    }

    public Appointments updateAppointment(Long appointmentId, AppointmentsDTO appointmentDTO) {
        Appointments appointment = appointmentsRepository.findById(appointmentId)
                .orElseThrow(() -> new RuntimeException("Appointment not found"));
        // Update appointment entity from appointmentDTO
        return saveAppointment(appointment);
    }

    public void deleteAppointment(Long appointmentsId) {
        appointmentsRepository.deleteById(appointmentsId);
    }

    public List<AppointmentsDTO> getAppointmentsByProvider(Long providerId) {
        List<Appointments> appointments = appointmentsRepository.findByHospitalStaffId(providerId);
        return appointments.stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public List<AppointmentsDTO> getAppointmentsByUser(Long userId) {
        List<Appointments> appointments = appointmentsRepository.findByUserId(userId);
        return appointments.stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    private AppointmentsDTO mapToDTO(Appointments appointment) {
        // Map appointment entity to AppointmentDTO
        return null;
    }

    private Appointments saveAppointment(Appointments appointment) {
        return appointmentsRepository.save(appointment);
    }

    public AppointmentsDTO createAppointmentsDTO(AppointmentsDTO appointmentsDTO) {
        return null;
    }
}