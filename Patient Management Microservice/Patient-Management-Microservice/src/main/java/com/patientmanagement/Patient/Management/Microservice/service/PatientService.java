package com.patientmanagement.Patient.Management.Microservice.service;

import com.patientmanagement.Patient.Management.Microservice.dto.PatientRequestDto;
import com.patientmanagement.Patient.Management.Microservice.dto.PatientResponseDto;
import com.patientmanagement.Patient.Management.Microservice.exception.EmailAlreadyExistsException;
import com.patientmanagement.Patient.Management.Microservice.exception.PatientNotFoundException;
import com.patientmanagement.Patient.Management.Microservice.mapper.PatientMapper;
import com.patientmanagement.Patient.Management.Microservice.model.Patient;
import com.patientmanagement.Patient.Management.Microservice.repository.PatientRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

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

    public PatientResponseDto updatePatient(UUID id, PatientRequestDto patientRequestDto ){

        // checking a person having same email and different id
        if(patientRepository.existsByEmailAndIdNot(patientRequestDto.getEmail(),id)){ // jpa
            throw new EmailAlreadyExistsException(" A patient with this email "+ "already exists" + patientRequestDto.getEmail());
        }

     Patient patient =  this.patientRepository.findById(id).orElseThrow(()->new PatientNotFoundException("Patient not found with Id: "+id));
     patient.setName(patientRequestDto.getName());
     patient.setEmail(patientRequestDto.getEmail());
     patient.setAddress(patientRequestDto.getAddress());
     patient.setDateOfBirth(LocalDate.parse(patientRequestDto.getDateOfBirth()));

    return toDTO( this.patientRepository.save(patient));


    }

    public void deletePatient(UUID id){
        this.patientRepository.deleteById(id);
    }


}
