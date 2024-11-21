//package com.tiba.tiba.Services;
//
//import com.tiba.tiba.DTO.ReferralDTO;
//import com.tiba.tiba.Entities.Referral;
//import com.tiba.tiba.Repositories.ReferralRepository;
//import jakarta.transaction.Transactional;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.time.LocalDateTime;
//import java.util.List;
//import java.util.stream.Collectors;
//import java.util.stream.Stream;
//
//@Service
//public class ReferralService {
//    @Autowired
//    private ReferralRepository referralRepository;
//
//    @Transactional
//    public ReferralDTO createReferral(ReferralDTO referralDTO) {
//        Referral referral = convertToEntity(referralDTO);
//        referral.setCreatedAt(LocalDateTime.now());
//        referral.setStatus(Referral.ReferralStatus.PENDING);
//
//        Referral savedReferral = referralRepository.save(referral);
//        return convertToDTO(savedReferral);
//    }
//
//    public ReferralDTO updateReferralStatus(Long referralId, Referral.ReferralStatus status) {
//        Referral referral = referralRepository.findById(referralId)
//                .orElseThrow(() -> new RuntimeException("Referral not found"));
//
//        referral.setStatus(status);
//        Referral updatedReferral = referralRepository.save(referral);
//        return convertToDTO(updatedReferral);
//    }
//
//    public List<ReferralDTO> getReferralsByDoctor(Long doctorId) {
//        List<Referral> referralsAsReferring = referralRepository.findByReferringDoctorId(doctorId);
//        List<Referral> referralsAsReferred = referralRepository.findByReferredDoctorId(doctorId);
//
//        List<Referral> allReferrals = Stream.concat(referralsAsReferring.stream(), referralsAsReferred.stream())
//                .collect(Collectors.toList());
//
//        return allReferrals.stream()
//                .map(this::convertToDTO)
//                .collect(Collectors.toList());
//    }
//
//    private Referral convertToEntity(ReferralDTO dto) {
//        Referral referral = new Referral();
//        referral.setHospitalStaffId(dto.getHospitalStaffId());
////        referral.setReferredDoctorId(dto.getReferredDoctorId());
//        referral.setPatientId(dto.getPatientId());
//        referral.setReferringHospitalId(dto.getReferringHospitalId());
//        referral.setReferredHospitalId(dto.getReferredHospitalId());
//        referral.setReason(dto.getReason());
//        referral.setStatus(Referral.ReferralStatus.valueOf(dto.getStatus()));
//        referral.setStatus(dto.getStatus());
//        return referral;
//    }
//
//    private ReferralDTO convertToDTO(Referral referral) {
//        ReferralDTO dto = new ReferralDTO();
//        dto.setId(referral.getId());
//        dto.setHospitalStaffId(referral.getHospitalStaffId());
////        dto.setReferredDoctorId(referral.getReferredDoctorId());
//        dto.setPatientId(referral.getPatientId());
//        dto.setReferringHospitalId(referral.getReferringHospitalId());
//        dto.setReferredHospitalId(referral.getReferredHospitalId());
//        dto.setReason(referral.getReason());
//        dto.setStatus(referral.getStatus().name());
//        dto.setCreatedAt(referral.getCreatedAt());
//        return dto;
//    }
//}
