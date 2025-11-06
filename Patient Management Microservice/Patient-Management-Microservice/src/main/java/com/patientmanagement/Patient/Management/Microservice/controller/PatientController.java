package com.patientmanagement.Patient.Management.Microservice.controller;

import com.patientmanagement.Patient.Management.Microservice.dto.PatientRequestDto;
import com.patientmanagement.Patient.Management.Microservice.dto.PatientResponseDto;
import com.patientmanagement.Patient.Management.Microservice.service.PatientService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/patients")
public class PatientController {

    @Autowired
    private PatientService patientService;

    @GetMapping
    public ResponseEntity<List<PatientResponseDto>> getPatients() {
        return new ResponseEntity<List<PatientResponseDto>>(this.patientService.getAllPatient(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<PatientResponseDto> createPatient(@Valid @RequestBody PatientRequestDto patientRequestDto) {

        return new ResponseEntity<>(this.patientService.createPatient(patientRequestDto), HttpStatus.CREATED);

    }


}
