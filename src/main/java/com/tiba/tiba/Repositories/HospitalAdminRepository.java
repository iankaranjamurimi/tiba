package com.tiba.tiba.Repositories;
import com.tiba.tiba.Entities.HospitalAdmin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HospitalAdminRepository extends JpaRepository<HospitalAdmin, Long> {
//   Optional<HospitalAdmin> findByEmail(String email);


}
