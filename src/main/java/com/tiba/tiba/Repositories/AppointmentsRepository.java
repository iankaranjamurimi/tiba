package com.tiba.tiba.Repositories;

import com.tiba.tiba.Entities.Appointments;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AppointmentsRepository extends JpaRepository<Appointments, Long> {
    List<Appointments> findByProviderId(Long providerId);
    List<Appointments> findByPatientId(Long patientId);
}
