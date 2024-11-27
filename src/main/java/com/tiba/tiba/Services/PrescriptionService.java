package com.tiba.tiba.Services;

import com.tiba.tiba.DTO.PrescriptionDTO;
import com.tiba.tiba.Entities.HospitalStaff;
import com.tiba.tiba.Entities.Prescription;
import com.tiba.tiba.Entities.User;
import com.tiba.tiba.Repositories.PrescriptionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PrescriptionService {
    private final PrescriptionRepository prescriptionRepository;

    // All prescriptions for a specific user
    public List<PrescriptionDTO> findPrescriptionsByUserId(Long userId) {
        List<Prescription> prescriptions = prescriptionRepository.findByUser_Id(userId);
        return prescriptions.stream()
                .map(this::mapEntityToDto)
                .collect(Collectors.toList());
    }

    public PrescriptionDTO createPrescription(PrescriptionDTO prescriptionDTO) {
        Prescription prescription = mapDtoToEntity(prescriptionDTO);
        Prescription savedPrescription = prescriptionRepository.save(prescription);
        return mapEntityToDto(savedPrescription);
    }

    private Prescription mapDtoToEntity(PrescriptionDTO prescriptionDTO) {
        Prescription prescription = new Prescription();
        prescription.setFrequency(prescriptionDTO.getFrequency());
        prescription.setDuration(prescriptionDTO.getDuration());
        prescription.setQuantity(prescriptionDTO.getQuantity());
        prescription.setStatus(prescriptionDTO.getStatus());
        prescription.setPrescribedDate(prescriptionDTO.getPrescribedDate());
        prescription.setNotes(prescriptionDTO.getNotes());
        prescription.setDosage(prescriptionDTO.getDosage());

        // Create User reference
        User user = new User();
        user.setId(prescriptionDTO.getUserId());
        prescription.setUser(user);

        // Add HospitalStaff reference
        HospitalStaff hospitalStaff = new HospitalStaff();
        hospitalStaff.setId(prescriptionDTO.getHospitalStaffId());
        prescription.setHospitalStaff(hospitalStaff);
        return prescription;
    }

    private PrescriptionDTO mapEntityToDto(Prescription prescription) {
        PrescriptionDTO prescriptionDTO = new PrescriptionDTO();
        prescriptionDTO.setFrequency(prescription.getFrequency());
        prescriptionDTO.setDuration(prescription.getDuration());
        prescriptionDTO.setQuantity(prescription.getQuantity());
        prescriptionDTO.setStatus(prescription.getStatus());
        prescriptionDTO.setPrescribedDate(prescription.getPrescribedDate());
        prescriptionDTO.setNotes(prescription.getNotes());
        prescriptionDTO.setDosage(prescription.getDosage());

        return prescriptionDTO;
    }
}























//package com.tiba.tiba.Services;
//
//import com.tiba.tiba.DTO.PrescriptionDTO;
//import com.tiba.tiba.Entities.HospitalStaff;
//import com.tiba.tiba.Entities.Prescription;
//import com.tiba.tiba.Entities.User;
//import com.tiba.tiba.Repositories.PrescriptionRepository;
//import jakarta.persistence.Id;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//import java.util.List;
//import java.util.stream.Collectors;
//
//@Service
//@RequiredArgsConstructor
//public class PrescriptionService {
//    private final PrescriptionRepository prescriptionRepository;
//
//    // Find all prescriptions for a specific user
//    public List<PrescriptionDTO> findPrescriptionsByUserId(Long userId) {
//        List<Prescription> prescriptions = prescriptionRepository.findByUser_Id(userId);
//        return prescriptions.stream()
//                .map(this::mapEntityToDto)
//                .collect(Collectors.toList());
//    }
//
//
//    public PrescriptionDTO createPrescription(PrescriptionDTO prescriptionDTO) {
//        Prescription prescription = mapDtoToEntity(prescriptionDTO);
//        Prescription savedPrescription = prescriptionRepository.save(prescription);
//        return mapEntityToDto(savedPrescription);
//    }
//
//    private Prescription mapDtoToEntity(PrescriptionDTO prescriptionDTO) {
//        Prescription prescription = new Prescription();
//        prescription.setFrequency(prescriptionDTO.getFrequency());
//        prescription.setDuration(prescriptionDTO.getDuration());
//        prescription.setQuantity(prescriptionDTO.getQuantity());
//        prescription.setStatus(prescriptionDTO.getStatus());
//        prescription.setPrescribedDate(prescriptionDTO.getPrescribedDate());
//        prescription.setNotes(prescriptionDTO.getNotes());
////        prescription.setUserId(prescriptionDTO.getUserId());
//        prescription.setDosage(prescriptionDTO.getDosage());
//
//        // Create User reference
//        User user = new User();
//        user.setId(prescriptionDTO.getUserId());
//        prescription.setUser(user);  // Use setUser instead of setUserId
//
//        // Add HospitalStaff reference
//        HospitalStaff hospitalStaff = new HospitalStaff();
//        hospitalStaff.setId(prescriptionDTO.getHospitalStaffId());
//        prescription.setHospitalStaff(hospitalStaff);
//        return prescription;
//    }
//
//    private PrescriptionDTO mapEntityToDto(Prescription prescription) {
//        PrescriptionDTO prescriptionDTO = new PrescriptionDTO();
//        prescriptionDTO.setFrequency(prescription.getFrequency());
//        prescriptionDTO.setDuration(prescription.getDuration());
//        prescriptionDTO.setQuantity(prescription.getQuantity());
//        prescriptionDTO.setStatus(prescription.getStatus());
//        prescriptionDTO.setPrescribedDate(prescription.getPrescribedDate());
//        prescriptionDTO.setNotes(prescription.getNotes());
////        prescriptionDTO.setUserId(prescription.getUser().getId());
//        prescriptionDTO.setDosage(prescription.getDosage());
//        prescriptionDTO.setUserId(prescription.getUser().getId());
//        prescriptionDTO.setHospitalStaffId(prescription.getHospitalStaff().getId());
//        return prescriptionDTO;
//    }
//}
