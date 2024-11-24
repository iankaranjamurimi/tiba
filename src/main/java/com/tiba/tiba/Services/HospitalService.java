package com.tiba.tiba.Services;

import com.tiba.tiba.Entities.Hospital;
import com.tiba.tiba.Repositories.HospitalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class HospitalService {
    private final HospitalRepository hospitalRepository;

    @Transactional
    public Hospital getOrCreateHospital(String hospitalName) {
        if (hospitalName == null || hospitalName.trim().isEmpty()) {
            return null;
        }

        return hospitalRepository.findByHospitalName(hospitalName)
                .orElseGet(() -> {
                    Hospital newHospital = new Hospital();
                    newHospital.setHospitalName(hospitalName);
                    return hospitalRepository.save(newHospital);
                });
    }
}