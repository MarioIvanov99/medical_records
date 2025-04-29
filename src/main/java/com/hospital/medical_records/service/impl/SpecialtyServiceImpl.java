package com.hospital.medical_records.service.impl;

import com.hospital.medical_records.data.entity.Specialty;
import com.hospital.medical_records.data.mapper.SpecialtyMapper;
import com.hospital.medical_records.data.repo.SpecialtyRepository;
import com.hospital.medical_records.dto.specialty.SpecialtyDTO;
import com.hospital.medical_records.exception.SpecialtyNotFoundException;
import com.hospital.medical_records.service.SpecialtyService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SpecialtyServiceImpl implements SpecialtyService {
    private final SpecialtyRepository specialtyRepository;
    private final SpecialtyMapper specialtyMapper;
    @Override
    @PreAuthorize("hasAnyRole('ADMIN', 'DOCTOR')")
    public List<SpecialtyDTO> getSpecialties() {
        return specialtyMapper.toResponseDtoList(specialtyRepository.findAll());
    }

    @Override
    @PreAuthorize("hasAnyRole('ADMIN', 'DOCTOR')")
    public SpecialtyDTO getSpecialtyById(long id) {
        Specialty specialty = specialtyRepository.findById(id)
                .orElseThrow(() -> new SpecialtyNotFoundException("Specialty not found", id));
        return specialtyMapper.toResponseDto(specialty);
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public SpecialtyDTO createSpecialty(SpecialtyDTO dto) {
        Specialty specialty = specialtyMapper.toEntity(dto);
        Specialty saved = specialtyRepository.save(specialty);
        return specialtyMapper.toResponseDto(saved);
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public Specialty updateSpecialty(long id, SpecialtyDTO dto) {
        Specialty existing = specialtyRepository.findById(id)
                .orElseThrow(() -> new SpecialtyNotFoundException("Specialty not found", id));
        specialtyMapper.updateSpecialty(dto, existing);
        return specialtyRepository.save(existing);
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteSpecialty(long id) {
        if (!specialtyRepository.existsById(id)) {
            throw new SpecialtyNotFoundException("Specialty not found", id);
        }
        specialtyRepository.deleteById(id);
    }

    @Override
    @PreAuthorize("hasAnyRole('ADMIN', 'DOCTOR')")
    public Specialty getDetailedSpecialtyById(long id) {
        return specialtyRepository.findById(id)
                .orElseThrow(() -> new SpecialtyNotFoundException("Specialty not found", id));
    }
}
