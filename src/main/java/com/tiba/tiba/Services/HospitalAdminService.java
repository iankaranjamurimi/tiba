package com.tiba.tiba.Services;

import com.tiba.tiba.DTO.HospitalAdminDTO;
import com.tiba.tiba.Entities.Hospital;
import com.tiba.tiba.Entities.HospitalAdmin;
import com.tiba.tiba.Repositories.HospitalAdminRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HospitalAdminService {

    @Autowired
    private HospitalAdminRepository hospitalAdminRepository;

    @Transactional
    public void registerUser(@Valid HospitalAdminDTO request) {
        if (hospitalAdminRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new RuntimeException("Email already exists!");
        }

        HospitalAdmin hospitalAdmin = new HospitalAdmin();
        HospitalAdmin.setEmail(request.getEmail());
        HospitalAdmin.setPassword(request.getPassword());
        HospitalAdmin.setFirstName(request.getFirstname());
        HospitalAdmin.setMiddleName(request.getMiddleName());
        HospitalAdmin.setLastName(request.getLastname());

        Hospital hospital = new Hospital();
        Hospital.setDateOfBirth(request.getDateOfBirth());
        Hospital.setGender(request.getGender());
        Hospital.setIdNumber(request.getIdNumber());
        Hospital.setContactNumber(request.getContactNumber());
        Hospital.setRoles(request.getRoles());

        hospitalAdmin.setHospital(hospital);
        hospital.setHospitalAdmin(hospitalAdmin);

        hospitalAdminRepository.save(hospitalAdmin);
    }

    public void register(@Valid HospitalAdminDTO request) {

    }
}
