package com.tiba.tiba.Services;

import com.tiba.tiba.DTO.HospitalStaffDTO;
import com.tiba.tiba.Entities.HospitalStaff;
import com.tiba.tiba.Entities.User;
import com.tiba.tiba.Repositories.HospitalStaffRepository;
import com.tiba.tiba.Repositories.UserSignUpRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class HospitalStaffService {

    private final HospitalStaffRepository hospitalStaffRepository;

    private final UserSignUpRepository userSignUpRepository;

    private final BCryptPasswordEncoder passwordEncoder;

    public HospitalStaffService(HospitalStaffRepository hospitalStaffRepository, UserSignUpRepository userSignUpRepository, BCryptPasswordEncoder passwordEncoder) {
        this.hospitalStaffRepository = hospitalStaffRepository;
        this.userSignUpRepository = userSignUpRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public void registerHospitalStaff(HospitalStaffDTO hospitalStaffDTO ) {

    if (userSignUpRepository.findByEmail(hospitalStaffDTO.getEmail()).isPresent()) {
        throw new RuntimeException("Email already exists!");
    }
                // Create and save User
                User user = new User();
                user.setFirstName(hospitalStaffDTO.getFirstName());
                user.setMiddleName(hospitalStaffDTO.getMiddleName());
                user.setLastName(hospitalStaffDTO.getLastName());
                user.setEmail(hospitalStaffDTO.getEmail());
                user.setPassword(passwordEncoder.encode(hospitalStaffDTO.getPassword()));
                user.setRoles(hospitalStaffDTO.getRoles());




                //Create and save HospitalStaff

                HospitalStaff hospitalStaff = new HospitalStaff();
                hospitalStaff.setPhoneNumber(hospitalStaffDTO.getPhoneNumber());
                hospitalStaff.setGender(hospitalStaffDTO.getGender());
                hospitalStaff.setIdNumber(hospitalStaffDTO.getIdNumber());
                hospitalStaff.setDateOfBirth(LocalDate.parse(hospitalStaffDTO.getDateOfBirth()));
                //hospitalStaff.setDateOfBirth(LocalDate.from(LocalDate.parse(hospitalStaffDTO.getDateOfBirth(), DateTimeFormatter.ofLocalizedPattern("yyyy-mm-dd")).atStartOfDay()));
                hospitalStaff.setAddress(hospitalStaffDTO.getAddress());
                hospitalStaff.setNationality(hospitalStaffDTO.getNationality());
                hospitalStaff.setUser (user);
                hospitalStaff.setHospitalName(hospitalStaffDTO.getHospitalname());

               userSignUpRepository.save(user);

               hospitalStaffRepository.save(hospitalStaff);

               hospitalStaffRepository.save(hospitalStaff);


//               userSignUpRepository.save(user);



        // Fetch the User entity using user Email
        //renamed user to users
        Optional<User> users = userSignUpRepository.findByEmail(hospitalStaffDTO.getEmail());
        users.ifPresent(hospitalStaff::setUser );

        hospitalStaff = hospitalStaffRepository.save(hospitalStaff);
        convertToDTO(hospitalStaff);
    }

    public HospitalStaffDTO getHospitalStaff(Integer idNumber) {
        Optional<HospitalStaff> hospitalStaff = hospitalStaffRepository.findByIdNumber(idNumber);
        return hospitalStaff.map(this::convertToDTO).orElse(null);
    }

    private HospitalStaffDTO convertToDTO(HospitalStaff hospitalStaff) {
        HospitalStaffDTO dto = new HospitalStaffDTO();
        dto.setPhoneNumber(hospitalStaff.getPhoneNumber());
        return dto;
    }
}



