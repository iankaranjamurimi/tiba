package com.tiba.tiba.Repositories;

import com.tiba.tiba.Entities.Referral;
import com.tiba.tiba.Entities.ReferralStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReferralRepository extends JpaRepository<Referral, Long> {
    List<Referral> findByReferringHospitalStaffId(Long hospitalStaffId);
    List<Referral> findByReferredHospitalStaffId(Long hospitalStaffId);
//    List<Referral> findByPatientId(Long patientId);
//    List<Referral> findByReferringHospitalId(Long hospitalId);
//    List<Referral> findByReferredHospitalId(Long hospitalId);
//    List<Referral> findByStatus(ReferralStatus status);
}
