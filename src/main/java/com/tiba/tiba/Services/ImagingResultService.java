//package com.tiba.tiba.Services;
//
//import com.tiba.tiba.DTO.ImagingResultDTO;
//import com.tiba.tiba.Entities.HospitalStaff;
//import com.tiba.tiba.Entities.ImagingResult;
//
//import com.tiba.tiba.Entities.User;
//import com.tiba.tiba.Repositories.ImagingResultRepository;
//import jakarta.persistence.EntityNotFoundException;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.io.IOException;
//
//import java.util.List;
//import java.util.stream.Collectors;
//
//@Service
//@RequiredArgsConstructor
//public class ImagingResultService {
//
//    private final ImagingResultRepository imagingResultRepository;
//    private final HospitalStaffService hospitalStaffService;
//    private final UserService userService;
//
//    @Transactional
//    public ImagingResultDTO createImagingResult(ImagingResultDTO resultDTO) throws IOException {
//        ImagingResult result = new ImagingResult();
//        result.setExamType(resultDTO.getExamType());
//        result.setExamDate(resultDTO.getExamDate());
//        result.setBodyPart(resultDTO.getBodyPart());
//        result.setFindings(resultDTO.getFindings());
//        result.setImpression(resultDTO.getImpression());
//        result.setStatus(resultDTO.getStatus());
//
//        // Set technician
//        HospitalStaff hospitalStaff = hospitalStaffService.getHospitalStaff(resultDTO.getHospitalStaff());
//        result.setHospitalStaff(hospitalStaff);
//
//        // Set patient
//        User patient = userService.getUserEntity(resultDTO.getPatientId());
//        result.setPatient(patient);
//
//        // Handle image upload
//        if (resultDTO.getImage() != null) {
//            result.setImageData(resultDTO.getImage().getBytes());
//            result.setImageType(getFileExtension(resultDTO.getImage().getOriginalFilename()));
//        }
//
//        ImagingResult savedResult = imagingResultRepository.save(result);
//        return mapToDTO(savedResult);
//    }
//
//    public ImagingResultDTO getImagingResult(Long id) {
//        ImagingResult result = imagingResultRepository.findById(id)
//                .orElseThrow(() -> new EntityNotFoundException("Imaging result not found with id: " + id));
//        return mapToDTO(result);
//    }
//
//    public List<ImagingResultDTO> getPatientImagingResults(Long patientId) {
//        return imagingResultRepository.findByPatientId(patientId).stream()
//                .map(this::mapToDTO)
//                .collect(Collectors.toList());
//    }
//
//    public List<ImagingResultDTO> getTechnicianImagingResults(Long technicianId) {
//        return imagingResultRepository.findByTechnicianId(technicianId).stream()
//                .map(this::mapToDTO)
//                .collect(Collectors.toList());
//    }
//
//    @Transactional
//    public ImagingResultDTO updateImagingResult(Long id, ImagingResultDTO resultDTO) throws IOException {
//        ImagingResult result = imagingResultRepository.findById(id)
//                .orElseThrow(() -> new EntityNotFoundException("Imaging result not found with id: " + id));
//
//        result.setFindings(resultDTO.getFindings());
//        result.setImpression(resultDTO.getImpression());
//        result.setStatus(resultDTO.getStatus());
//
//        if (resultDTO.getImage() != null) {
//            result.setImageData(resultDTO.getImage().getBytes());
//            result.setImageType(getFileExtension(resultDTO.getImage().getOriginalFilename()));
//        }
//
//        return mapToDTO(imagingResultRepository.save(result));
//    }
//
//    private String getFileExtension(String fileName) {
//        return fileName.substring(fileName.lastIndexOf(".") + 1).toUpperCase();
//    }
//
//    private ImagingResultDTO mapToDTO(ImagingResult result) {
//        ImagingResultDTO dto = new ImagingResultDTO();
//        dto.setId(result.getId());
//        dto.setExamType(result.getExamType());
//        dto.setExamDate(result.getExamDate());
//        dto.setBodyPart(result.getBodyPart());
//        dto.setFindings(result.getFindings());
//        dto.setImpression(result.getImpression());
//        dto.setStatus(result.getStatus());
//        dto.setTechnicianId(result.getTechnician().getId());
//        dto.setPatientId(result.getPatient().getId());
//        dto.setCreatedAt(result.getCreatedAt());
//        dto.setUpdatedAt(result.getUpdatedAt());
//        dto.setImageData(result.getImageData());
//        return dto;
//    }
//}