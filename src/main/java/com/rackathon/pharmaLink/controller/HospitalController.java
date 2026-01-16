package com.rackathon.pharmaLink.controller;

import com.rackathon.pharmaLink.entity.Hospital;
import com.rackathon.pharmaLink.service.HospitalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/hospitals") // <--- Note the different URL!
public class HospitalController {

    @Autowired
    private HospitalService hospitalService;

    @PostMapping
    public Hospital registerHospital(@RequestBody Hospital hospital) {
        return hospitalService.registerHospital(hospital);
    }

    @GetMapping
    public List<Hospital> getAllHospitals() {
        return hospitalService.getAllHospitals();
    }
}