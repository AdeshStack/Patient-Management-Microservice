package com.patientmanagement.Patient.Management.Microservice.repository;

import com.patientmanagement.Patient.Management.Microservice.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PatientRepository extends JpaRepository<Patient, UUID> {

    //add extra  method to check the exitsbyemail
    boolean existsByEmail(String email);

    //checking a person has same email in the database
    boolean existsByEmailAndIdNot(String email,UUID id);
}
