package com.tiba.tiba.Controllers;

import com.tiba.tiba.DTO.ImagingResultDTO;
import com.tiba.tiba.Services.ImagingResultService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;

@RestController
@RequestMapping("/api/open")
public class ImagingResultController {
    private final ImagingResultService imagingResultService;

    public ImagingResultController(ImagingResultService imagingResultService) {
        this.imagingResultService = imagingResultService;
    }

    @PostMapping("/imaging-results")
    public ResponseEntity<ImagingResultDTO> uploadImagingResult(
//            @Valid @ModelAttribute ImagingResultDTO imagingResultDTO
            @Valid @RequestPart("file")  ImagingResultDTO imagingResultDTO
    ) throws IOException {
        // Validate if file is present
        if (imagingResultDTO.getImageFile() == null || imagingResultDTO.getImageFile().isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        ImagingResultDTO savedDTO = imagingResultService.createImagingResult(imagingResultDTO);
        return ResponseEntity.ok(savedDTO);
    }

    @GetMapping("/upload/{id}")
    public ResponseEntity<ImagingResultDTO> getImagingResult(@PathVariable Long id) {
        return imagingResultService.getImagingResultById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteImagingResult(@PathVariable Long id) throws IOException {
        imagingResultService.deleteImagingResult(id);
        return ResponseEntity.ok().build();
    }
}