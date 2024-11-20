package com.tiba.tiba.Repositories;

import com.tiba.tiba.Entities.Appointments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppointmentsRepository extends JpaRepository<Appointments, Long> {
    List<Appointments> findByUserId(Long userId);
}
