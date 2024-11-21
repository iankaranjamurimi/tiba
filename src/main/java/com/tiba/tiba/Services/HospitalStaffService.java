package com.tiba.tiba.Services;

import com.tiba.tiba.DTO.HospitalStaffDTO;
import com.tiba.tiba.Entities.HospitalStaff;
import com.tiba.tiba.Entities.User;
import com.tiba.tiba.Repositories.HospitalStaffRepository;
import com.tiba.tiba.Repositories.UserSignUpRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class HospitalStaffService {

    private final HospitalStaffRepository hospitalStaffRepository;
    private final UserSignUpRepository userSignUpRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Transactional
    public HospitalStaffDTO registerHospitalStaff(HospitalStaffDTO hospitalStaffDTO) {
        // Validate email uniqueness
        userSignUpRepository.findByEmail(hospitalStaffDTO.getEmail())
                .ifPresent(existingUser -> {
                    throw new RuntimeException("Email already exists!");
                });

        // Create User Entity
        User user = createUserEntity(hospitalStaffDTO);

        // Create HospitalStaff Entity
        HospitalStaff hospitalStaff = createHospitalStaffEntity(hospitalStaffDTO, user);

        // Save entities
        try {
            userSignUpRepository.save(user);
            hospitalStaffRepository.save(hospitalStaff);
        } catch (Exception e) {
            throw new RuntimeException("Error saving hospital staff: " + e.getMessage(), e);
        }

        // Convert and return DTO
        return convertToDTO(hospitalStaff);
    }

    private User createUserEntity(HospitalStaffDTO dto) {
        User user = new User();
        user.setFirstName(dto.getFirstName());
        user.setMiddleName(dto.getMiddleName());
        user.setLastName(dto.getLastName());
        user.setEmail(dto.getEmail());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setRoles(dto.getRoles());
        return user;
    }

    private HospitalStaff createHospitalStaffEntity(HospitalStaffDTO dto, User user) {
        HospitalStaff hospitalStaff = new HospitalStaff();
        hospitalStaff.setPhoneNumber(dto.getPhoneNumber());
        hospitalStaff.setGender(dto.getGender());
        hospitalStaff.setIdNumber(dto.getIdNumber());

        // Safely parse date of birth
        try {
            hospitalStaff.setDateOfBirth(LocalDate.parse(dto.getDateOfBirth()));
        } catch (Exception e) {
            throw new RuntimeException("Invalid date of birth format. Please use YYYY-MM-DD", e);
        }

        hospitalStaff.setAddress(dto.getAddress());
        hospitalStaff.setNationality(dto.getNationality());
        hospitalStaff.setUser(user);
        hospitalStaff.setHospitalName(dto.getHospitalname());

        return hospitalStaff;
    }

    public HospitalStaffDTO getHospitalStaff(Integer idNumber) {
        Optional<HospitalStaff> hospitalStaff = hospitalStaffRepository.findByIdNumber(idNumber);
        return hospitalStaff.map(this::convertToDTO)
                .orElseThrow(() -> new RuntimeException("Hospital staff not found with ID: " + idNumber));
    }

    private HospitalStaffDTO convertToDTO(HospitalStaff hospitalStaff) {
        HospitalStaffDTO dto = new HospitalStaffDTO();

        // User details
        User user = hospitalStaff.getUser();
        dto.setFirstName(user.getFirstName());
        dto.setMiddleName(user.getMiddleName());
        dto.setLastName(user.getLastName());
        dto.setEmail(user.getEmail());
        dto.setRoles(user.getRoles());

        // Hospital Staff details
        dto.setPhoneNumber(hospitalStaff.getPhoneNumber());
        dto.setGender(hospitalStaff.getGender());
        dto.setIdNumber(hospitalStaff.getIdNumber());
        dto.setDateOfBirth(hospitalStaff.getDateOfBirth().toString());
        dto.setAddress(hospitalStaff.getAddress());
        dto.setNationality(hospitalStaff.getNationality());
        dto.setHospitalname(hospitalStaff.getHospitalName());

        // Note: Do not set password when returning DTO
        return dto;
    }
}