package com.hospital.medical_records.data.repo;

import com.hospital.medical_records.data.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PatientRepository extends JpaRepository<Patient, Long> {
    List<Patient> findDistinctByVisits_Diagnoses_Id(long diagnosisId);
    List<Patient> findDistinctByVisits_Diagnoses_Afflictions_Name(String afflictionName);
    List<Patient> findByGp_Id(Long gpId);
    List<Patient> findByGp_Name(String gpName);
}
