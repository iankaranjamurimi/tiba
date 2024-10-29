package com.tiba.tiba.Services;

import com.tiba.tiba.DTO.HospitalAdminDTO;
import com.tiba.tiba.Entities.Hospital;
import com.tiba.tiba.Entities.HospitalAdmin;
import com.tiba.tiba.Entities.User;
import com.tiba.tiba.Repositories.HospitalAdminRepository;
import com.tiba.tiba.Repositories.HospitalRepository;
import com.tiba.tiba.Repositories.UserRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HospitalAdminService {

    @Autowired
    private HospitalAdminRepository hospitalAdminRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    HospitalRepository hospitalRepository;

    @Transactional
    public void registerUser (@Valid HospitalAdminDTO request) {
        // Check if the email already exists
        if (userRepository.findByEmail(request.Email()).isPresent()) {
            throw new RuntimeException("Email already exists!");
        }

        User user = new User();
        user.setFirstName(request.getFirstName());
        user.setMiddleName(request.getMiddleName());
        user.setLastName(request.getLastName());
        user.setPassword(request.getPassword());
        user.setEmail(request.getEmail());
        user.setRoles(request.getRoles());


        HospitalAdmin hospitalAdmin = new HospitalAdmin();
        hospitalAdmin.setContactNumber(request.getContactNumber());
        hospitalAdmin.setIdNumber(request.getIdNumber());
        hospitalAdmin.setGender(request.getGender());
        hospitalAdmin.setAddress(request.getAddress());
        hospitalAdmin.setDateOfBirth(request.getDateOfBirth());


        // Hospital
        Hospital hospital = new Hospital();
        hospital.setHospitalName(request.getHospitalName());
        hospital.setHospitalAddress(request.getHospitalAddress());
        hospital.setHospitalLocation(request.getHospitalLocation());
        hospital.setHospitalContactNumber(request.getHospitalContactNumber());


        // Set relationships
        hospitalAdmin.setUser(user);
        hospitalAdmin.setHospital(hospital);
        hospital.setHospitalAdmin(hospitalAdmin);

        // Save the hospital admin
        userRepository.save(user);

        // Save the hospital
        hospitalRepository.save(hospital);

        // Save the hospital admin
        hospitalAdminRepository.save(hospitalAdmin);
    }
}