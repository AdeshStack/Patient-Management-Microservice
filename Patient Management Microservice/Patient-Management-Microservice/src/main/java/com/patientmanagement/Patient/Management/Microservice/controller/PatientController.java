package com.patientmanagement.Patient.Management.Microservice.controller;

import com.patientmanagement.Patient.Management.Microservice.dto.PatientRequestDto;
import com.patientmanagement.Patient.Management.Microservice.dto.PatientResponseDto;
import com.patientmanagement.Patient.Management.Microservice.dto.validators.createPatientValidationGroup;
import com.patientmanagement.Patient.Management.Microservice.service.PatientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.groups.Default;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/patients")
@Tag(name="Patient",description = "API for managing Patients") // using swagger //check in localhost:4000/v3/api-doc
public class PatientController {

    @Autowired
    private PatientService patientService;

    @GetMapping
    @Operation(summary = "Get Patients")
    public ResponseEntity<List<PatientResponseDto>> getPatients() {
        return new ResponseEntity<List<PatientResponseDto>>(this.patientService.getAllPatient(), HttpStatus.OK);
    }

    @PostMapping
    @Operation(summary = "Create a new Patient")
    public ResponseEntity<PatientResponseDto> createPatient(@Validated({Default.class, createPatientValidationGroup.class})
                                                                @RequestBody PatientRequestDto patientRequestDto) {

        return new ResponseEntity<>(this.patientService.createPatient(patientRequestDto), HttpStatus.CREATED);

    }
    @PutMapping("/{id}")
    @Operation(summary = "Update the User")
    public ResponseEntity<PatientResponseDto> updatePatient(@PathVariable("id") UUID id,@Validated({Default.class}) @RequestBody PatientRequestDto patientRequestDto){

        return new ResponseEntity<>(this.patientService.updatePatient(id,patientRequestDto),HttpStatus.OK);

    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete the user")
    public ResponseEntity<Void> deletePatient(@PathVariable("id") UUID id){
        patientService.deletePatient(id);
        return ResponseEntity.noContent().build();
    }



}
