package com.tiba.tiba.Services;

import com.tiba.tiba.DTO.ReferralDTO;
import com.tiba.tiba.Entities.Referral;
import com.tiba.tiba.Entities.ReferralStatus;
import com.tiba.tiba.Repositories.ReferralRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class ReferralService {

    private final ReferralRepository referralRepository;

    public ReferralService(ReferralRepository referralRepository) {
        this.referralRepository = referralRepository;
    }

    @Transactional
    public ReferralDTO createReferral(ReferralDTO referralDTO) {
        Referral referral = convertToEntity(referralDTO);
        referral.setCreatedAt(LocalDateTime.now());
        referral.setStatus(ReferralStatus.PENDING);

        Referral savedReferral = referralRepository.save(referral);
        return convertToDTO(savedReferral);
    }

    public ReferralDTO updateReferralStatus(Long referralId, ReferralStatus status) {
        Referral referral = referralRepository.findById(referralId)
                .orElseThrow(() -> new RuntimeException("Referral not found"));

        referral.setStatus(status);
        Referral updatedReferral = referralRepository.save(referral);
        return convertToDTO(updatedReferral);
    }

    public List<ReferralDTO> getReferralsByDoctor(Long hospitalStaffId) {
        List<Referral> referralsAsReferring = referralRepository.findByReferringHospitalStaffId(hospitalStaffId);
        List<Referral> referralsAsReferred = referralRepository.findByReferredHospitalStaffId(hospitalStaffId);

        List<Referral> allReferrals = Stream.concat(referralsAsReferring.stream(), referralsAsReferred.stream())
                .toList();

        return allReferrals.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public List<ReferralDTO> getReferralsByHospital(Long hospitalId) {
        // Get both the incoming and outgoing referrals for the hospital
        List<Referral> incomingReferrals = referralRepository.findByReferredHospitalId(hospitalId);
        List<Referral> outgoingReferrals = referralRepository.findByReferringHospitalId(hospitalId);
        // Combine both lists and convert to DTOs
        return Stream.concat(incomingReferrals.stream(), outgoingReferrals.stream())
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private Referral convertToEntity(ReferralDTO dto) {
        Referral referral = new Referral();
        referral.setReferringHospitalStaffId(dto.getReferringHospitalStaffId());;
        referral.setReferredHospitalStaffId(dto.getReferredHospitalStaffId());
        referral.setPatientId(dto.getPatientId());
        referral.setReferringHospitalId(dto.getReferringHospitalId());
        referral.setReferredHospitalId(dto.getReferredHospitalId());
        referral.setReason(dto.getReason());
        referral.setStatus(dto.getStatus());
        referral.setStatus(dto.getStatus());
        return referral;
    }

    private ReferralDTO convertToDTO(Referral referral) {
        ReferralDTO dto = new ReferralDTO();
        dto.setId(referral.getId());
        dto.setReferringHospitalStaffId(referral.getReferringHospitalStaffId());
        dto.setReferredHospitalStaffId(referral.getReferredHospitalStaffId());
        dto.setPatientId(referral.getPatientId());
        dto.setReferringHospitalId(referral.getReferringHospitalId());
        dto.setReferredHospitalId(referral.getReferredHospitalId());
        dto.setReason(referral.getReason());
        dto.setStatus(referral.getStatus());
        dto.setCreatedAt(referral.getCreatedAt());
        return dto;
    }
}
