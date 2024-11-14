package com.tiba.tiba.Controllers;

import com.tiba.tiba.DTO.LabResultsDTO;
import com.tiba.tiba.Services.LabResultsService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/open")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class LabResultsController {
    private final LabResultsService labResultsService;

    @PostMapping("/lab-results")
    public ResponseEntity<LabResultsDTO> createLabResults(@Valid @RequestBody LabResultsDTO labResultsDTO) {
        LabResultsDTO createdLabResults = labResultsService.createLabResults(labResultsDTO);
        return new ResponseEntity<>(createdLabResults, HttpStatus.CREATED);
    }
}

