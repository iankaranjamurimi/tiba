package com.tiba.tiba.Repositories;

import com.tiba.tiba.Entities.Prescription;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PrescriptionRepository extends JpaRepository<Prescription, Long> {
}
