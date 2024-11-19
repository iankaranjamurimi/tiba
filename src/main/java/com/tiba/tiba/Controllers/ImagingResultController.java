//package com.tiba.tiba.Controllers;
//
//import com.tiba.tiba.DTO.ImagingResultDTO;
//import com.tiba.tiba.Services.ImagingResultService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.multipart.MultipartFile;
//import java.io.IOException;
//import java.time.LocalDateTime;
//import java.util.List;
//
//@RestController
//@RequestMapping("/api/imaging-results")
//@RequiredArgsConstructor
//public class ImagingResultController {
//
//    private final ImagingResultService imagingResultService;
//
//    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
//    public ResponseEntity<ImagingResultDTO> createImagingResult(
//            @RequestParam("image") MultipartFile image,
//            @RequestParam("examType") String examType,
//            @RequestParam("examDate") String examDate,
//            @RequestParam("bodyPart") String bodyPart,
//            @RequestParam("findings") String findings,
//            @RequestParam("impression") String impression,
//            @RequestParam("status") String status,
//            @RequestParam("technicianId") Long technicianId,
//            @RequestParam("patientId") Long patientId) throws IOException {
//
//        ImagingResultDTO dto = new ImagingResultDTO();
//        dto.setImage(image);
//        dto.setExamType(examType);
//        dto.setExamDate(LocalDateTime.parse(examDate));
//        dto.setBodyPart(bodyPart);
//        dto.setFindings(findings);
//        dto.setImpression(impression);
//        dto.setStatus(status);
//        dto.setTechnicianId(technicianId);
//        dto.setPatientId(patientId);
//
//        return ResponseEntity.ok(imagingResultService.createImagingResult(dto));
//    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<ImagingResultDTO> getImagingResult(@PathVariable Long id) {
//        return ResponseEntity.ok(imagingResultService.getImagingResult(id));
//    }
//
//    @GetMapping("/patient/{patientId}")
//    public ResponseEntity<List<ImagingResultDTO>> getPatientImagingResults(@PathVariable Long patientId) {
//        return ResponseEntity.ok(imagingResultService.getPatientImagingResults(patientId));
//    }
//
//    @GetMapping("/technician/{technicianId}")
//    public ResponseEntity<List<ImagingResultDTO>> getTechnicianImagingResults(@PathVariable Long technicianId) {
//        return ResponseEntity.ok(imagingResultService.getTechnicianImagingResults(technicianId));
//    }
//
//    @PutMapping("/{id}")
//    public ResponseEntity<ImagingResultDTO> updateImagingResult(
//            @PathVariable Long id,
//            @RequestParam(required = false) MultipartFile image,
//            @RequestParam String findings,
//            @RequestParam String impression,
//            @RequestParam String status) throws IOException {
//
//        ImagingResultDTO dto = new ImagingResultDTO();
//        dto.setImage(image);
//        dto.setFindings(findings);
//        dto.setImpression(impression);
//        dto.setStatus(status);
//
//        return ResponseEntity.ok(imagingResultService.updateImagingResult(id, dto));
//    }
//}