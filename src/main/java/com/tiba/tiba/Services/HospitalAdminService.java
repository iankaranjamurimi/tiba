package com.tiba.tiba.Services;

import com.tiba.tiba.DTO.HospitalAdminDTO;
import com.tiba.tiba.Entities.Hospital;
import com.tiba.tiba.Entities.HospitalAdmin;
import com.tiba.tiba.Entities.User;
import com.tiba.tiba.Repositories.HospitalAdminRepository;
import com.tiba.tiba.Repositories.HospitalRepository;
import com.tiba.tiba.Repositories.UserSignUpRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HospitalAdminService {

    private final HospitalAdminRepository hospitalAdminRepository;
    private final UserSignUpRepository userSignUpRepository;
    private final HospitalRepository hospitalRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Transactional
    public HospitalAdminDTO registerUser(@Valid HospitalAdminDTO request) {
        // Validating the  email
        userSignUpRepository.findByEmail(request.getEmail())
                .ifPresent(existingUser -> {
                    throw new RuntimeException("Email already exists!");
                });

        User user = createUserEntity(request);

        Hospital hospital = createHospitalEntity(request);

        HospitalAdmin hospitalAdmin = createHospitalAdminEntity(request, user, hospital);

        try {
            userSignUpRepository.save(user);
            hospitalRepository.save(hospital);
            hospitalAdminRepository.save(hospitalAdmin);
        } catch (Exception e) {
            throw new RuntimeException("Error saving hospital admin: " + e.getMessage(), e);
        }

        return mapToDTO(hospitalAdmin);
    }



    @Transactional
    public void deleteAdmin(Long id) {
        HospitalAdmin hospitalAdmin = hospitalAdminRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Hospital admin not found with id: " + id));

        // Getting the associated entities
        User user = hospitalAdmin.getUser();
        Hospital hospital = hospitalAdmin.getHospital();

        // Removing the relationships
        hospital.setHospitalAdmin(null);
        hospitalAdmin.setHospital(null);
        hospitalAdmin.setUser(null);

        // Deleting the  entities
        hospitalAdminRepository.delete(hospitalAdmin);
        hospitalRepository.delete(hospital);
        userSignUpRepository.delete(user);
    }


    private User createUserEntity(HospitalAdminDTO request) {
        User user = new User();
        user.setFirstName(request.getFirstName());
        user.setMiddleName(request.getMiddleName());
        user.setLastName(request.getLastName());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setEmail(request.getEmail());
        user.setRoles(request.getRoles());
        return user;
    }

    private Hospital createHospitalEntity(HospitalAdminDTO request) {
        Hospital hospital = new Hospital();
        hospital.setHospitalName(request.getHospitalName());
        hospital.setHospitalAddress(request.getHospitalAddress());
        hospital.setHospitalLocation(request.getHospitalLocation());
        hospital.setHospitalContactNumber(request.getHospitalContactNumber());
        return hospital;
    }

    private HospitalAdmin createHospitalAdminEntity(HospitalAdminDTO request, User user, Hospital hospital) {
        HospitalAdmin hospitalAdmin = new HospitalAdmin();
        hospitalAdmin.setPhoneNumber(request.getPhoneNumber());
        hospitalAdmin.setIdNumber(request.getIdNumber());
        hospitalAdmin.setGender(request.getGender());
        hospitalAdmin.setAddress(request.getAddress());
        hospitalAdmin.setDateOfBirth(request.getDateOfBirth());

        // Setting the relationships
        hospitalAdmin.setUser(user);
        hospitalAdmin.setHospital(hospital);
        hospital.setHospitalAdmin(hospitalAdmin);

        return hospitalAdmin;
    }

    private HospitalAdminDTO mapToDTO(HospitalAdmin hospitalAdmin) {
        HospitalAdminDTO dto = new HospitalAdminDTO();

        // Mapping user
        User user = hospitalAdmin.getUser();
        dto.setFirstName(user.getFirstName());
        dto.setMiddleName(user.getMiddleName());
        dto.setLastName(user.getLastName());
        dto.setEmail(user.getEmail());
        dto.setRoles(user.getRoles());

        // Mapping hospital admin
        dto.setPhoneNumber(hospitalAdmin.getPhoneNumber());
        dto.setIdNumber(hospitalAdmin.getIdNumber());
        dto.setGender(hospitalAdmin.getGender());
        dto.setAddress(hospitalAdmin.getAddress());
        dto.setDateOfBirth(hospitalAdmin.getDateOfBirth());

        // Mapping hospital
        Hospital hospital = hospitalAdmin.getHospital();
        dto.setHospitalName(hospital.getHospitalName());
        dto.setHospitalAddress(hospital.getHospitalAddress());
        dto.setHospitalLocation(hospital.getHospitalLocation());
        dto.setHospitalContactNumber(hospital.getHospitalContactNumber());

        return dto;
    }
}