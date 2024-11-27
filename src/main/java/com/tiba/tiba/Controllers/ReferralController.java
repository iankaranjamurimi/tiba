package com.tiba.tiba.Controllers;

import com.tiba.tiba.DTO.ReferralDTO;
import com.tiba.tiba.Entities.ReferralStatus;
import com.tiba.tiba.Services.ReferralService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @GetMapping("/allHospitalReferrals/{hospitalId}")
    public ResponseEntity<Map<String, Object>> getHospitalReferrals(@PathVariable Long hospitalId) {
        Map<String, Object> response = new HashMap<>();
           try {
                List<ReferralDTO> referrals = referralService.getReferralsByHospital(hospitalId);

                response.put("success", true);
                response.put("message", "Referrals retrieved successfully");
                response.put("data", referrals);
                return ResponseEntity.ok(response);

            } catch (Exception e) {
                response.put("success", false);
                response.put("message", "Failed to retrieve hospital referrals");
                response.put("error", e.getMessage());
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body(response);
            }
    }
}
