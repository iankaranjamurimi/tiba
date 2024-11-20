package com.tiba.tiba.Controllers;

import com.tiba.tiba.DTO.AppointmentsDTO;
import com.tiba.tiba.Services.AppointmentsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/open")
@RequiredArgsConstructor
public class AppointmentsController {
    private final AppointmentsService appointmentsService;

    @PostMapping("/appointments")
    public ResponseEntity<AppointmentsDTO> createAppointment(@RequestBody AppointmentsDTO appointmentsDTO) {
        AppointmentsDTO createdAppointment = appointmentsService.createAppointment(appointmentsDTO);
        return ResponseEntity.ok(createdAppointment);
    }

    @GetMapping("/appointments/{userId}")
    public ResponseEntity<List<AppointmentsDTO>> getAppointmentsByUserId(@PathVariable Long userId) {
        List<AppointmentsDTO> appointments = appointmentsService.getAppointmentsByUserId(userId);
        return ResponseEntity.ok(appointments);
    }
}
