package com.tiba.tiba.Services;

import com.tiba.tiba.DTO.HospitalAdminDTO;
import com.tiba.tiba.Entities.Hospital;
import com.tiba.tiba.Entities.HospitalAdmin;
import com.tiba.tiba.Entities.User;
import com.tiba.tiba.Repositories.HospitalAdminRepository;
import com.tiba.tiba.Repositories.HospitalRepository;
import com.tiba.tiba.Repositories.UserSignUpRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class HospitalAdminService {

    private final HospitalAdminRepository hospitalAdminRepository;

    private final UserSignUpRepository userSignUpRepository;

    private final HospitalRepository hospitalRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public HospitalAdminService(HospitalAdminRepository hospitalAdminRepository, UserSignUpRepository userSignUpRepository, HospitalRepository hospitalRepository) {
        this.hospitalAdminRepository = hospitalAdminRepository;
        this.userSignUpRepository = userSignUpRepository;
        this.hospitalRepository = hospitalRepository;
    }

    @Transactional
    public void registerUser (@Valid HospitalAdminDTO request) {

        // Check if the email already exists
        if (userSignUpRepository.findByEmail(request.Email()).isPresent()) {
            throw new RuntimeException("Email already exists!");
        }

        User user = new User();
        user.setFirstName(request.getFirstName());
        user.setMiddleName(request.getMiddleName());
        user.setLastName(request.getLastName());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setEmail(request.getEmail());
        user.setRoles(request.getRoles());


        HospitalAdmin hospitalAdmin = new HospitalAdmin();
        hospitalAdmin.setPhoneNumber(request.getPhoneNumber());
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


        // Setting relationships
        hospitalAdmin.setUser(user);
        hospitalAdmin.setHospital(hospital);
        hospital.setHospitalAdmin(hospitalAdmin);

        // Save hospital admin
        userSignUpRepository.save(user);

        // Save  hospital
        hospitalRepository.save(hospital);

        // Save hospital admin
        hospitalAdminRepository.save(hospitalAdmin);

//        userSignUpRepository.save(user);

//        user.setHospitalAdmin(hospitalAdmin);
//        hospitalAdmin.setUser(user);
    }

}