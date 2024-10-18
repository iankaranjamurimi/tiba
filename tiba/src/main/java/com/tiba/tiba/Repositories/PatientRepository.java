package com.tiba.tiba.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends JpaRepository<com.tiba.tiba.Entities.Patient, Integer> {
}