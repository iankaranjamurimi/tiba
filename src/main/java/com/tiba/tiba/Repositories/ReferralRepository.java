//package com.tiba.tiba.Repositories;
//
//import com.tiba.tiba.Entities.Referral;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.stereotype.Repository;
//
//import java.util.List;
//
//@Repository
//public interface ReferralRepository extends JpaRepository<Referral, Long> {
//    List<Referral> findByReferringDoctorId(Long doctorId);
//    List<Referral> findByReferredDoctorId(Long doctorId);
//    List<Referral> findByPatientId(Long patientId);
//    List<Referral> findByReferringHospitalId(Long hospitalId);
//    List<Referral> findByReferredHospitalId(Long hospitalId);
//    List<Referral> findByStatus(Referral.ReferralStatus status);
//}
