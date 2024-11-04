package com.tiba.tiba.Controllers;

import com.tiba.tiba.DTO.AllergiesDTO;
import com.tiba.tiba.Services.AllergiesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/open")
@RequiredArgsConstructor
public class AllergiesController {
    private final AllergiesService allergiesService;

    @PutMapping("/{id}")
    public ResponseEntity<AllergiesDTO> updateAllergies(
            @PathVariable Long id,
            @RequestBody AllergiesDTO allergiesDTO) {
        AllergiesDTO updatedAllergies = allergiesService.updateAllergies(id, allergiesDTO);
        return ResponseEntity.ok(updatedAllergies);
    }
}

