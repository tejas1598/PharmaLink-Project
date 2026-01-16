package com.rackathon.pharmaLink.service;

import com.rackathon.pharmaLink.entity.Hospital;
import com.rackathon.pharmaLink.repository.HospitalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class HospitalService {

    @Autowired
    private HospitalRepository hospitalRepository;

    public Hospital registerHospital(Hospital hospital) {
        // Logic: We could check if email already exists here later
        return hospitalRepository.save(hospital);
    }

    public List<Hospital> getAllHospitals() {
        return hospitalRepository.findAll();
    }
}