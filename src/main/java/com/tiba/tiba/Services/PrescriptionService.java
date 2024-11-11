package com.tiba.tiba.Services;

import com.tiba.tiba.DTO.PrescriptionDTO;
import com.tiba.tiba.Entities.Prescription;
import com.tiba.tiba.Repositories.PrescriptionRepository;
import org.springframework.stereotype.Service;

@Service
public class PrescriptionService {
    private final PrescriptionRepository prescriptionRepository;

    public PrescriptionService(PrescriptionRepository prescriptionRepository) {
        this.prescriptionRepository = prescriptionRepository;
    }

    public PrescriptionDTO createPrescription(PrescriptionDTO prescriptionDTO) {
        Prescription prescription = mapDtoToEntity(prescriptionDTO);
        Prescription savedPrescription = prescriptionRepository.save(prescription);
        return mapEntityToDto(savedPrescription);
    }

    public PrescriptionDTO getPrescriptionById(Long id) {
        Prescription prescription = prescriptionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Prescription not found with id: " + id));
        return mapEntityToDto(prescription);
    }

    // Add more CRUD methods as needed

    private Prescription mapDtoToEntity(PrescriptionDTO prescriptionDTO) {
        Prescription prescription = new Prescription();
        prescription.setPrescriptionId(prescriptionDTO.getPrescriptionId());
        prescription.setDosage(prescriptionDTO.getDosage());
        // Map other fields
        return prescription;
    }

    private PrescriptionDTO mapEntityToDto(Prescription prescription) {
        PrescriptionDTO prescriptionDTO = new PrescriptionDTO();
        prescriptionDTO.setPrescriptionId(prescription.getPrescriptionId());
        prescriptionDTO.setDosage(prescription.getDosage());
        // Map other fields
        return prescriptionDTO;
    }
}