package com.tiba.tiba.Controllers;

import com.tiba.tiba.DTO.AllergiesDTO;
import com.tiba.tiba.Services.AllergiesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/open")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class AllergiesController {
    private final AllergiesService allergiesService;

    @PostMapping("/allergies/{userId}")
    public ResponseEntity<AllergiesDTO> updateAllergies(
            @PathVariable Long userId,
            @RequestBody AllergiesDTO allergiesDTO) {
        AllergiesDTO updatedAllergies = allergiesService.updateAllergies(userId, allergiesDTO);
        return ResponseEntity.ok(updatedAllergies);
    }
}