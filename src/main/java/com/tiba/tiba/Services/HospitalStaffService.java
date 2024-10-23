package com.tiba.tiba.Services;

import com.tiba.tiba.DTO.HospitalStaffDTO;
import com.tiba.tiba.Entities.HospitalStaff;
import com.tiba.tiba.Entities.User;
import com.tiba.tiba.Repositories.HospitalStaffRepository;
import com.tiba.tiba.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class HospitalStaffService {

    @Autowired
    private HospitalStaffRepository hospitalStaffRepository;

    @Autowired
    private UserRepository userRepository;

    public HospitalStaffDTO createHospitalStaff(HospitalStaffDTO hospitalStaffDTO) {
        HospitalStaff hospitalStaff = new HospitalStaff();
        hospitalStaff.setEmail(hospitalStaffDTO.getEmail());
        hospitalStaff.setPhoneNumber(hospitalStaffDTO.getPhoneNumber());
        hospitalStaff.setSpecialization(hospitalStaffDTO.getSpecialization());

        // Fetch the User entity using userId
        Optional<User> user = userRepository.findById(hospitalStaffDTO.getHospitalStaff_Id());
        user.ifPresent(hospitalStaff::setUser );

        hospitalStaff = hospitalStaffRepository.save(hospitalStaff);
        return convertToDTO(hospitalStaff);
    }

    public HospitalStaffDTO getHospitalStaff(Integer id) {
        Optional<HospitalStaff> hospitalStaff = hospitalStaffRepository.findById(id);
        return hospitalStaff.map(this::convertToDTO).orElse(null);
    }

    private HospitalStaffDTO convertToDTO(HospitalStaff hospitalStaff) {
        HospitalStaffDTO dto = new HospitalStaffDTO();
        dto.setEmail(hospitalStaff.getEmail());
        dto.setPhoneNumber(hospitalStaff.getPhoneNumber());
        dto.setSpecialization(hospitalStaff.getSpecialization());
        return dto;
    }
}



