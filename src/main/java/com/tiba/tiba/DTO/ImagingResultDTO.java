package com.tiba.tiba.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ImagingResultDTO {
    private String examType; // X-Ray, MRI, CT Scan, Ultrasound
    private String bodyPart;
    private String results;
    private LocalDate createdAt;
    private String submittedBy;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Long userId;

    // Transient field for file upload
    private MultipartFile imageFile;

    // Cloudinary-specific fields
    private String imagePublicId;
    private String imageUrl;
}
