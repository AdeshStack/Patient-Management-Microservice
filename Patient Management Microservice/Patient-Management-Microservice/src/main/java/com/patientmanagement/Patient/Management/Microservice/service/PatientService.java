package com.patientmanagement.Patient.Management.Microservice.service;

import com.patientmanagement.Patient.Management.Microservice.dto.PatientRequestDto;
import com.patientmanagement.Patient.Management.Microservice.dto.PatientResponseDto;
import com.patientmanagement.Patient.Management.Microservice.exception.EmailAlreadyExistsException;
import com.patientmanagement.Patient.Management.Microservice.mapper.PatientMapper;
import com.patientmanagement.Patient.Management.Microservice.model.Patient;
import com.patientmanagement.Patient.Management.Microservice.repository.PatientRepository;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.patientmanagement.Patient.Management.Microservice.mapper.PatientMapper.toDTO;

@Service
public class PatientService {

    private PatientRepository patientRepository;

    public  PatientService(PatientRepository patientRepository){
        this.patientRepository=patientRepository;
    }

    public  PatientResponseDto createPatient(PatientRequestDto patientRequestDto){
        if(patientRepository.existsByEmail(patientRequestDto.getEmail())){ // jpa
            throw new EmailAlreadyExistsException(" A patient with this email "+ "already exists" + patientRequestDto.getEmail());
        }

        Patient p= this.patientRepository.save(PatientMapper.toModel(patientRequestDto));
        return PatientMapper.toDTO(p);
    }

    public List<PatientResponseDto> getAllPatient(){
        List<PatientResponseDto> responseList=patientRepository.findAll().stream().map(patient -> toDTO(patient)).toList();
        return responseList;
    }


}
