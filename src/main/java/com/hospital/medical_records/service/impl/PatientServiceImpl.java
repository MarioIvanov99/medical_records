package com.hospital.medical_records.service.impl;

import com.hospital.medical_records.data.entity.Doctor;
import com.hospital.medical_records.data.entity.Patient;
import com.hospital.medical_records.data.entity.Role;
import com.hospital.medical_records.data.mapper.PatientMapper;
import com.hospital.medical_records.data.repo.PatientRepository;
import com.hospital.medical_records.dto.patient.*;
import com.hospital.medical_records.exception.PatientNotFoundException;
import com.hospital.medical_records.service.PatientService;
import com.hospital.medical_records.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class PatientServiceImpl implements PatientService {
    private final PatientRepository patientRepository;
    private final PatientMapper patientMapper;
    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;
    @Override
    @PreAuthorize("hasAnyRole('ADMIN', 'DOCTOR')")
    public List<PatientResponseDTO> getPatients(){
        return patientMapper.toResponseDtoList(patientRepository.findAll());
    }
    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public List<PatientDetailedResponseDTO> getDetailedPatients(){
        return patientMapper.toDetailedResponseDtoList(patientRepository.findAll());
    }
    @Override
    @PreAuthorize("hasAnyRole('ADMIN', 'DOCTOR')")
    public PatientResponseDTO getPatientById(long id){
        return patientMapper.toResponseDto(patientRepository.findById(id)
                .orElseThrow(() -> new PatientNotFoundException("Patient not found", id))
        );
    }
    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public PatientDetailedResponseDTO getDetailedPatientById(long id){
        return patientMapper.toDetailedResponseDto(patientRepository.findById(id)
                .orElseThrow(() -> new PatientNotFoundException("Patient not found", id))
        );
    }

    @Override
    public Patient getPatientEntityById(long id){
        return patientRepository.findById(id)
                .orElseThrow(() -> new PatientNotFoundException("Patient not found", id));
    }

    @Override
    @PreAuthorize("hasAnyRole('ADMIN', 'DOCTOR')")
    public List<PatientNameCodeDTO> getPatientsByDiagnosisId(long diagnosisId){
        return patientMapper.toNameCodeDtoList(patientRepository.findDistinctByVisits_Diagnoses_Id(diagnosisId));
    }

    @Override
    @PreAuthorize("hasAnyRole('ADMIN', 'DOCTOR')")
    public List<PatientNameCodeDTO> getPatientsByAfflictionName(String afflictionName){
        return patientMapper.toNameCodeDtoList(patientRepository.findDistinctByVisits_Diagnoses_Afflictions_Name(afflictionName));
    }

    @Override
    @PreAuthorize("hasAnyRole('ADMIN', 'DOCTOR')")
    public List<PatientNameCodeDTO> getPatientsByGpId(long id){
        return patientMapper.toNameCodeDtoList(patientRepository.findByGp_Id(id));
    }

    @Override
    @PreAuthorize("hasAnyRole('ADMIN', 'DOCTOR')")
    public List<PatientNameCodeDTO> getPatientsByGpName(String name){
        return patientMapper.toNameCodeDtoList(patientRepository.findByGp_Name(name));
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public CreatePatientDTO createPatient(CreatePatientDTO dto){
        Patient patient = patientMapper.toEntity(dto);
        patient.setPassword(passwordEncoder.encode(dto.getPassword()));
        initializePatientAccount(patient);
        Patient saved = patientRepository.save(patient);
        return dto;
    }
    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public Patient updatePatient(long id, UpdatePatientDTO dto){
        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new PatientNotFoundException("Patient not found", id));
        patient.setPassword(passwordEncoder.encode(dto.getPassword()));
        patientMapper.updatePatient(dto, patient);
        return patientRepository.save(patient);
    }
    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public void deletePatient(long id){
        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new PatientNotFoundException("Patient not found", id));
        deactivatePatientAccount(patient);
        patientRepository.deleteById(id);
    }

    private void initializePatientAccount(Patient patient) {
        patient.setEnabled(true);
        patient.setAccountNonExpired(true);
        patient.setAccountNonLocked(true);
        patient.setCredentialsNonExpired(true);
        patient.setActive(true);
        Role role = roleService.getRoleByName("PATIENT");
        patient.setAuthorities(Set.of(role));
        role.getUsers().add(patient);
    }

    private void deactivatePatientAccount(Patient patient) {
        patient.setEnabled(false);
        patient.setAccountNonExpired(false);
        patient.setAccountNonLocked(false);
        patient.setCredentialsNonExpired(false);

        patient.setActive(false);
    }
}
