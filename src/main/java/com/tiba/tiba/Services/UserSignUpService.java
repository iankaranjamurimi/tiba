package com.tiba.tiba.Services;

import com.tiba.tiba.DTO.UserSignUpDTO;
import com.tiba.tiba.Entities.Patient;
import com.tiba.tiba.Entities.User;
import com.tiba.tiba.Repositories.UserSignUpRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserSignUpService {

    private final UserSignUpRepository userSignUpRepository;

    @Transactional
    public void registerUser(@Valid UserSignUpDTO request) {
        if (userSignUpRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new RuntimeException("Email already exists!");
        }

        User user = new User();
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        user.setFirstName(request.getFirstname());
        user.setMiddleName(request.getMiddlename());
        user.setLastName(request.getLastname());
        user.setRoles(request.getRoles());

        Patient patient = new Patient();
        patient.setDateOfBirth(request.getDateOfBirth());
        patient.setGender(request.getGender());
        patient.setIdNumber(request.getIdNumber());
        patient.setContactNumber(request.getContactNumber());

        user.setPatient(patient);
        patient.setUser(user);

        userSignUpRepository.save(user);
    }


}
