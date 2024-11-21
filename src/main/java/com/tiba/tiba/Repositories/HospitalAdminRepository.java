package com.tiba.tiba.Repositories;
import com.tiba.tiba.Entities.HospitalAdmin;
import com.tiba.tiba.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface HospitalAdminRepository extends JpaRepository<HospitalAdmin, Long> {
    Optional<HospitalAdmin> findByUser(User user);
}
