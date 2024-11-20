package com.tiba.tiba.Services;

import com.tiba.tiba.DTO.AppointmentsDTO;
import com.tiba.tiba.Entities.Appointments;
import com.tiba.tiba.Entities.HospitalStaff;
import com.tiba.tiba.Entities.User;
import com.tiba.tiba.Repositories.AppointmentsRepository;
import com.tiba.tiba.Repositories.HospitalStaffRepository;
import com.tiba.tiba.Repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AppointmentsService {
    private final AppointmentsRepository appointmentsRepository;
    private final UserRepository userRepository;
    private final HospitalStaffRepository hospitalStaffRepository;

    public AppointmentsDTO createAppointment(AppointmentsDTO appointmentDTO) {
        User user = userRepository.findById(appointmentDTO.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        HospitalStaff hospitalStaff = hospitalStaffRepository.findById(appointmentDTO.getHospitalStaffId())
                .orElseThrow(() -> new RuntimeException("Hospital Staff not found"));

        Appointments appointment = convertToEntity(appointmentDTO, user, hospitalStaff);
        Appointments savedAppointment = appointmentsRepository.save(appointment);
        return convertToDTO(savedAppointment);
    }

    public List<AppointmentsDTO> getAppointmentsByUserId(Long userId) {
        List<Appointments> appointments = appointmentsRepository.findByUserId(userId);
        return appointments.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private Appointments convertToEntity(AppointmentsDTO dto, User user, HospitalStaff hospitalStaff) {
        Appointments appointment = new Appointments();
        appointment.setUser(user);
        appointment.setAppointmentType(dto.getAppointmentType());
        appointment.setStartTime(dto.getStartTime());
        appointment.setStatus(dto.getStatus());
        appointment.setSubmittedAt(dto.getSubmittedAt());
        appointment.setSubmittedBy(dto.getSubmittedBy());
        appointment.setHospitalStaff(hospitalStaff);

        return appointment;
    }

    private AppointmentsDTO convertToDTO(Appointments entity) {
        AppointmentsDTO dto = new AppointmentsDTO();
        dto.setUserId(entity.getUser().getId());
        dto.setAppointmentType(entity.getAppointmentType());
        dto.setStartTime(entity.getStartTime());
        dto.setStatus(entity.getStatus());
        dto.setSubmittedAt(entity.getSubmittedAt());
        dto.setSubmittedBy(entity.getSubmittedBy());
        dto.setHospitalStaffId(entity.getHospitalStaff().getId());
        return dto;
    }
}
