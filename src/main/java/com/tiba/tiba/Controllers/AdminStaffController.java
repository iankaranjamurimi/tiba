package com.tiba.tiba.Controllers;

import com.tiba.tiba.DTO.*;
import com.tiba.tiba.Models.ApiResponse;
import com.tiba.tiba.Entities.User;
import com.tiba.tiba.Entities.HospitalAdmin;
import com.tiba.tiba.Entities.HospitalStaff;
import com.tiba.tiba.Entities.Patient;
import com.tiba.tiba.Repositories.UserSignUpRepository;
import com.tiba.tiba.Repositories.HospitalAdminRepository;
import com.tiba.tiba.Repositories.HospitalStaffRepository;
import com.tiba.tiba.Services.*;
import jakarta.validation.Valid;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/open")
@CrossOrigin(origins = "*")
public class AdminStaffController {

    private final UserSignUpRepository userSignUpRepository;
    private final HospitalAdminRepository hospitalAdminRepository;
    private final HospitalStaffRepository hospitalStaffRepository;
    private final UserService userService;
    private final HospitalStaffService hospitalStaffService;
    private final HospitalAdminService hospitalAdminService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Getter
    private final PatientService patientService;
    private final JwtUtil jwtUtil;

    public AdminStaffController(
            UserSignUpRepository userSignUpRepository,
            HospitalAdminRepository hospitalAdminRepository,
            HospitalStaffRepository hospitalStaffRepository,
            UserService userService,
            HospitalAdminService hospitalAdminService,
            HospitalStaffService hospitalStaffService,
            PatientService patientService,
            BCryptPasswordEncoder bCryptPasswordEncoder,
            JwtUtil jwtUtil) {
        this.userSignUpRepository = userSignUpRepository;
        this.hospitalAdminRepository = hospitalAdminRepository;
        this.hospitalStaffRepository = hospitalStaffRepository;
        this.userService = userService;
        this.hospitalAdminService = hospitalAdminService;
        this.hospitalStaffService = hospitalStaffService;
        this.patientService = patientService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.jwtUtil = jwtUtil;
    }

    // Authentication endpoint
    @PostMapping("/admin/staff/login")
    public ResponseEntity<SignUpResponseDTO<AdminStaffLogInResponseDTO>> login(@RequestBody UserLogInDTO request) {
        if (!StringUtils.hasText(request.getEmail()) || !StringUtils.hasText(request.getPassword())) {
            return ResponseEntity.badRequest()
                    .body(SignUpResponseDTO.error("Both Email and password are required"));
        }

        Optional<User> existingUser = userSignUpRepository.findByEmail(request.getEmail());
        if (existingUser.isEmpty()) {
            return ResponseEntity.badRequest()
                    .body(SignUpResponseDTO.error("User was not found"));
        }

        User user = existingUser.get();
        if (!userService.verifyPassword(request.getPassword(), user.getPassword())) {
            return ResponseEntity.badRequest()
                    .body(SignUpResponseDTO.error("Invalid password"));
        }

        String hospitalName = null;
        Optional<HospitalAdmin> hospitalAdmin = hospitalAdminRepository.findByUser(user);
        if (hospitalAdmin.isPresent()) {
            hospitalName = hospitalAdmin.get().getHospital().getHospitalName();
        } else {
            Optional<HospitalStaff> hospitalStaff = hospitalStaffRepository.findByUser(user);
            if (hospitalStaff.isPresent()) {
                hospitalName = hospitalStaff.get().getHospitalName();
            }
        }

        AdminStaffLogInResponseDTO userResponse = convertToDTO(user, hospitalName);
        return ResponseEntity.ok().body(new SignUpResponseDTO<>(true, "Logged in successfully", userResponse));
    }

    // Hospital Admin endpoints
    @PostMapping("/create/hospitaladmin")
    public ResponseEntity<ApiResponse> createHospitalAdmin(@Valid @RequestBody HospitalAdminDTO request) {
        try {
            if (request.getPassword() == null || request.getPassword().isEmpty()) {
                return ResponseEntity.badRequest().body(
                        ApiResponse.badRequest("Password cannot be empty")
                );
            }

            String hashedPassword = bCryptPasswordEncoder.encode(request.getPassword());
            request.setPassword(hashedPassword);

            HospitalAdminDTO registeredAdmin = hospitalAdminService.registerUser(request);

            return ResponseEntity.status(HttpStatus.CREATED).body(
                    ApiResponse.ok("Hospital Admin registered successfully", registeredAdmin)
            );
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(
                    ApiResponse.badRequest(e.getMessage())
            );
        }
    }

    @DeleteMapping("/hospitaladmin/{id}")
    public ResponseEntity<ApiResponse> deleteHospitalAdmin(@PathVariable Long id) {
        try {
            hospitalAdminService.deleteAdmin(id);
            return ResponseEntity.ok(
                    ApiResponse.ok("Hospital admin deleted successfully")
            );
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(
                    ApiResponse.badRequest(e.getMessage())
            );
        }
    }

    @GetMapping("/hospital/admins")
    public ResponseEntity<List<HospitalAdminDTO>> getAllHospitalAdmins() {
        List<HospitalAdminDTO> hospitalAdmins = hospitalAdminService.getAllHospitalAdmins();
        return ResponseEntity.ok(hospitalAdmins);
    }

    // Hospital Staff endpoints
    @PostMapping("/create/hospitalstaff")
    public ResponseEntity<ApiResponse> createHospitalStaff(@Valid @RequestBody HospitalStaffDTO hospitalStaffDTO) {
        try {
            if (hospitalStaffDTO.getPassword() == null || hospitalStaffDTO.getPassword().isEmpty()) {
                return ResponseEntity.badRequest().body(
                        ApiResponse.badRequest("Password cannot be empty")
                );
            }

            String hashedPassword = bCryptPasswordEncoder.encode(hospitalStaffDTO.getPassword());
            hospitalStaffDTO.setPassword(hashedPassword);

            HospitalStaffDTO registeredStaff = hospitalStaffService.registerHospitalStaff(hospitalStaffDTO);

            return ResponseEntity.status(HttpStatus.CREATED).body(
                    new ApiResponse(true, "Hospital Staff created successfully", HttpStatus.CREATED, registeredStaff)
            );
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(
                    ApiResponse.badRequest(e.getMessage())
            );
        }
    }

    @GetMapping("/hospitalstaff/{id}")
    public ResponseEntity<ApiResponse> getHospitalStaff(@PathVariable Integer id) {
        try {
            HospitalStaffDTO hospitalStaffDTO = hospitalStaffService.getHospitalStaff(id);
            if (hospitalStaffDTO != null) {
                return ResponseEntity.ok(
                        ApiResponse.ok("Hospital staff retrieved successfully", hospitalStaffDTO)
                );
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        ApiResponse.notFound("Hospital staff not found")
                );
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(
                    ApiResponse.badRequest(e.getMessage())
            );
        }
    }

    @DeleteMapping("/hospitalstaff/{id}")
    public ResponseEntity<ApiResponse> deleteHospitalStaff(@PathVariable Integer id) {
        try {
            hospitalStaffService.deleteHospitalStaff(id);
            return ResponseEntity.ok(
                    ApiResponse.ok("Hospital staff deleted successfully")
            );
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(
                    ApiResponse.badRequest(e.getMessage())
            );
        }
    }

    @GetMapping("/hospitalstaff/all")
    public ResponseEntity<List<HospitalStaffDTO>> getAllHospitalStaff() {
        List<HospitalStaffDTO> hospitalStaffList = hospitalStaffService.getAllHospitalStaff();
        return ResponseEntity.ok(hospitalStaffList);
    }

    // Patient endpoints
    @GetMapping("/patients")
    public List<Patient> getAllPatients() {
        return patientService.getAllPatients();
    }

    private AdminStaffLogInResponseDTO convertToDTO(User user, String hospitalName) {
        AdminStaffLogInResponseDTO dto = new AdminStaffLogInResponseDTO();
        dto.setId(user.getId());
        dto.setEmail(user.getEmail());
        dto.setFirstName(user.getFirstName());
        dto.setLastName(user.getLastName());
        dto.setToken(jwtUtil.generateToken(user.getEmail(), user.getRoles()));
        dto.setRoles(user.getRoles());
        dto.setHospitalName(hospitalName);
        return dto;
    }
}