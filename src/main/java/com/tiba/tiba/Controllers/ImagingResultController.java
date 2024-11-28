package com.tiba.tiba.Controllers;

import com.tiba.tiba.DTO.ImagingResultDTO;
import com.tiba.tiba.Services.ImagingResultService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/open")
@RequiredArgsConstructor
public class ImagingResultController {
    private final ImagingResultService imagingResultService;

    @PostMapping("/{user_id}/imaging-results")
    public ResponseEntity<Map<String, Object>> uploadImagingResult(
            @PathVariable("user_id") Long userId,
            @Valid @ModelAttribute ImagingResultDTO imagingResultDTO
    ) {
        Map<String, Object> response = new HashMap<>();

        // Validate if file is present
        if (imagingResultDTO.getImageFile() == null || imagingResultDTO.getImageFile().isEmpty()) {
            response.put("success", false);
            response.put("message", "Image file is required");
            return ResponseEntity.badRequest().body(response);
        }


        try {
            ImagingResultDTO savedDTO = imagingResultService.createImagingResult(imagingResultDTO);
            response.put("success", true);
            response.put("message", "Imaging result uploaded successfully");
            response.put("data", savedDTO); // Include the saved DTO in the response
            return ResponseEntity.ok(response);
        } catch (IOException e) {
            response.put("success", false);
            response.put("message", "Failed to upload imaging result");
            response.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @GetMapping("/upload/{userId}")
    public ResponseEntity<List<ImagingResultDTO>> getImagingResultsByUser(@PathVariable Long userId) {
        try {
            List<ImagingResultDTO> results = imagingResultService.getImagingResultsByUser(userId);

            if (results.isEmpty()) {
                return ResponseEntity.noContent().build();
            }

            return ResponseEntity.ok(results);
        } catch (RuntimeException e) {
            // Handle the case where user is not found
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteImagingResult(@PathVariable Long id) throws IOException {
        imagingResultService.deleteImagingResult(id);
        return ResponseEntity.ok().build();
    }
}