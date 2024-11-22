package com.tiba.tiba.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;

@Data
public class ImagingResultDTO {
    private Long id;

    @NotBlank(message = "Exam type is required")
    @Size(min = 2, max = 50, message = "Exam type must be between 2 and 50 characters")
    private String examType; // X-Ray, MRI, CT Scan, Ultrasound

    @NotBlank(message = "Body part is required")
    @Size(min = 2, max = 50, message = "Body part must be between 2 and 50 characters")
    private String bodyPart; // Chest, Head, Abdomen

    @Size(max = 1000, message = "Results cannot exceed 1000 characters")
    private String results;

    private LocalDate createdAt;

    @NotBlank(message = "Submitted by is required")
    @Size(min = 2, max = 100, message = "Submitted by must be between 2 and 100 characters")
    private String submittedBy;

    // Transient field for file upload
    private MultipartFile imageFile;

    // Cloudinary-specific fields (optional to expose)
    private String imagePublicId;
    private String imageUrl;
}
