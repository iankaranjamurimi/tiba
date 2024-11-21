package com.tiba.tiba.Repositories;

import com.tiba.tiba.Entities.Referral;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ReferralRepository extends JpaRepository<Referral, Long> {
    List<Referral> findByReferringHospitalStaffId(Long hospitalStaffId);
    List<Referral> findByReferredHospitalStaffId(Long hospitalStaffId);
}
