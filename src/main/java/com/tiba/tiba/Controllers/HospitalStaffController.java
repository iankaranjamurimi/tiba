package com.tiba.tiba.Controllers;

import com.tiba.tiba.DTO.HospitalStaffDTO;
import com.tiba.tiba.Services.HospitalStaffService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/open")
@CrossOrigin(origins = "*")
public class HospitalStaffController {

    @Autowired
    private HospitalStaffService hospitalStaffService;

    @GetMapping("/hospitalstaff/{id}")
    public ResponseEntity<HospitalStaffDTO> getHospitalStaff(@PathVariable Integer id) {
        HospitalStaffDTO hospitalStaffDTO = hospitalStaffService.getHospitalStaff(id);
        if (hospitalStaffDTO != null) {
            return ResponseEntity.ok(hospitalStaffDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/create/hospitalstaff")
    public ResponseEntity<ApiResponse> createHospitalStaff(@Valid @RequestBody HospitalStaffDTO hospitalStaffDTO) {
        try {
            hospitalStaffService.registerHospitalStaff(hospitalStaffDTO);

            ApiResponse response = new ApiResponse(200, "Hospital Staff created successfully");
             return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            ApiResponse errorResponse = new ApiResponse(400, e.getMessage());
            return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
        }
        
        
    }
    @Data
    @AllArgsConstructor
    class ApiResponse {
        private int status;
        private String message;
    }
}



    
    
    
 
    
   

        


        

    


    

   


//    @PostMapping("/create/hospitalstaff")
//    public ResponseEntity<String> createHospitalStaff(@Valid @RequestBody HospitalStaffDTO hospitalStaffDTO) {
//        try {
//            hospitalStaffService.registerHospitalStaff(hospitalStaffDTO);
//            return new ResponseEntity<>("Hospital Staff created successfully", HttpStatus.CREATED);
//        } catch (Exception e) {
//            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
//        }
//    }







