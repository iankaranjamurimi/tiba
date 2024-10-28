package com.tiba.tiba.Repositories;

import com.tiba.tiba.Entities.HospitalAdmin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface HospitalAdminRepository extends JpaRepository<HospitalAdmin, Long> {
    Optional<HospitalAdmin> findByEmail(String email);


}
