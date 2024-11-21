package com.tiba.tiba.Controllers;

import com.tiba.tiba.DTO.ReferralDTO;
import com.tiba.tiba.Entities.ReferralStatus;
import com.tiba.tiba.Services.ReferralService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/open")
public class ReferralController {
    @Autowired
    private ReferralService referralService;

    @PostMapping("/referrals")
    public ResponseEntity<ReferralDTO> createReferral(@Valid @RequestBody ReferralDTO referralDTO) {
        ReferralDTO createdReferral = referralService.createReferral(referralDTO);
        return ResponseEntity.ok(createdReferral);
    }

    @PutMapping("/{referralId}/status")
    public ResponseEntity<ReferralDTO> updateReferralStatus(
            @PathVariable Long referralId,
            @RequestParam  ReferralStatus status
    ) {
        ReferralDTO updatedReferral = referralService.updateReferralStatus(referralId, status);
        return ResponseEntity.ok(updatedReferral);
    }

    @GetMapping("/doctor/{hospitalStaffId}")
    public ResponseEntity<List<ReferralDTO>> getReferralsByDoctor(@PathVariable Long hospitalStaffId) {
        List<ReferralDTO> referrals = referralService.getReferralsByDoctor(hospitalStaffId);
        return ResponseEntity.ok(referrals);
    }
}
