package com.hospital.medical_records.service.impl;

import com.hospital.medical_records.data.entity.Doctor;
import com.hospital.medical_records.data.entity.Role;
import com.hospital.medical_records.data.entity.Specialty;
import com.hospital.medical_records.data.mapper.DoctorMapper;
import com.hospital.medical_records.data.repo.DoctorRepository;
import com.hospital.medical_records.dto.doctor.CreateDoctorDTO;
import com.hospital.medical_records.dto.doctor.DoctorCountResponseDTO;
import com.hospital.medical_records.dto.doctor.DoctorResponseDTO;
import com.hospital.medical_records.dto.doctor.UpdateDoctorDTO;
import com.hospital.medical_records.exception.DoctorNotFoundException;
import com.hospital.medical_records.service.DoctorService;
import com.hospital.medical_records.service.RoleService;
import com.hospital.medical_records.service.SpecialtyService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DoctorServiceImpl implements DoctorService {
    private final DoctorRepository doctorRepository;
    private final SpecialtyService specialtyService;
    private final DoctorMapper doctorMapper;
    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;

    @Override
    @PreAuthorize("hasAnyRole('ADMIN', 'DOCTOR')")
    public List<DoctorResponseDTO> getDoctors() {
        return doctorMapper.toResponseDtoList(doctorRepository.findAll());
    }

    @Override
    @PreAuthorize("hasAnyRole('ADMIN', 'DOCTOR')")
    public DoctorResponseDTO getDoctorById(long id) {
        Doctor doctor = doctorRepository.findById(id)
                .orElseThrow(() -> new DoctorNotFoundException("Doctor not found", id));
        return doctorMapper.toResponseDto(doctor);
    }

    @Override
    public Doctor getDoctorEntityById(long id) {
        return doctorRepository.findById(id)
                .orElseThrow(() -> new DoctorNotFoundException("Doctor not found", id));
    }

    @Override
    @PreAuthorize("hasAnyRole('ADMIN', 'DOCTOR')")
    public List<DoctorCountResponseDTO> getGpsByPatientCount() {
        return doctorRepository.findGpPatientCounts();
    }

    @Override
    @PreAuthorize("hasAnyRole('ADMIN', 'DOCTOR')")
    public List<DoctorCountResponseDTO> getDoctorsByVisitCount() {
        return doctorRepository.findDoctorVisitCounts();
    }

    @Override
    @PreAuthorize("hasAnyRole('ADMIN', 'DOCTOR')")
    public List<DoctorCountResponseDTO> getDoctorsWithMostSicKLeaveRecords() {
        return doctorRepository.findDoctorsWithMostSickLeaveRecords();
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public CreateDoctorDTO createDoctor(CreateDoctorDTO dto) {
        Doctor doctor = doctorMapper.toEntity(dto);
        doctor.setPassword(passwordEncoder.encode(dto.getPassword()));
        updateDoctorSpecialties(doctor, dto.getSpecialtyIds());
        initializeDoctorAccount(doctor);
        Doctor saved = doctorRepository.save(doctor);
        return dto;
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public UpdateDoctorDTO updateDoctor(long id, UpdateDoctorDTO dto) {
        Doctor doctor = doctorRepository.findById(id)
                .orElseThrow(() -> new DoctorNotFoundException("Doctor not found", id));
        doctorMapper.updateDoctor(dto, doctor);
        doctor.setPassword(passwordEncoder.encode(dto.getPassword()));
        updateDoctorSpecialties(doctor, dto.getSpecialtyIds());
        Doctor saved = doctorRepository.save(doctor);
        return dto;
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteDoctor(long id) {
        Doctor doctor = doctorRepository.findById(id)
                .orElseThrow(() -> new DoctorNotFoundException("Doctor not found", id));
        deactivateDoctorAccount(doctor);
        Doctor saved = doctorRepository.save(doctor);
    }

    private void updateDoctorSpecialties(Doctor doctor, Set<Long> specialtyIds) {
        Set<Specialty> specialties = specialtyIds.stream()
                .map(specialtyService::getDetailedSpecialtyById)
                .collect(Collectors.toSet());
        doctor.setSpecialties(specialties);
    }

    private void initializeDoctorAccount(Doctor doctor) {
        doctor.setEnabled(true);
        doctor.setAccountNonExpired(true);
        doctor.setAccountNonLocked(true);
        doctor.setCredentialsNonExpired(true);
        doctor.setActive(true);
        Role role = roleService.getRoleByName("DOCTOR");
        doctor.setAuthorities(Set.of(role));
        role.getUsers().add(doctor);
    }

    private void deactivateDoctorAccount(Doctor doctor) {
        doctor.setEnabled(false);
        doctor.setAccountNonExpired(false);
        doctor.setAccountNonLocked(false);
        doctor.setCredentialsNonExpired(false);
        doctor.setActive(false);
    }
}
