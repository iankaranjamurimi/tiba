package com.tiba.tiba.Controllers;

import com.tiba.tiba.DTO.AppointmentsDTO;
import com.tiba.tiba.Entities.Appointments;
import com.tiba.tiba.Services.AppointmentsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/open")
@RequiredArgsConstructor
public class AppointmentsController {

    private final AppointmentsService appointmentsService;

    @PostMapping("/appointments")
    public AppointmentsDTO createAppointment(@RequestBody AppointmentsDTO appointmentDTO) {
        return appointmentsService.createAppointmentsDTO(appointmentDTO);
    }

    @PutMapping("/{id}")
    public Appointments updateAppointment(@PathVariable Long id, @RequestBody AppointmentsDTO appointmentDTO) {
        return appointmentsService.updateAppointment(id, appointmentDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteAppointment(@PathVariable Long id) {
        appointmentsService.deleteAppointment(id);
    }

    @GetMapping("/provider/{providerId}")
    public List<AppointmentsDTO> getAppointmentsByProvider(@PathVariable Long providerId) {
        return appointmentsService.getAppointmentsByProvider(providerId);
    }

    @GetMapping("/patient/{patientId}")
    public List<AppointmentsDTO> getAppointmentsByPatient(@PathVariable Long patientId) {
        return appointmentsService.getAppointmentsByPatient(patientId);
    }
}