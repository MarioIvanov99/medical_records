package com.hospital.medical_records.service.impl;

import com.hospital.medical_records.data.entity.Diagnosis;
import com.hospital.medical_records.data.mapper.DiagnosisMapper;
import com.hospital.medical_records.data.repo.DiagnosisRepository;
import com.hospital.medical_records.dto.diagnosis.CreateDiagnosisDTO;
import com.hospital.medical_records.dto.diagnosis.DiagnosisResponseDTO;
import com.hospital.medical_records.dto.diagnosis.UpdateDiagnosisDTO;
import com.hospital.medical_records.exception.DiagnosisNotFoundException;
import com.hospital.medical_records.service.DiagnosisService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class DiagnosisServiceImpl implements DiagnosisService {
    private final DiagnosisRepository diagnosisRepository;
    private final DiagnosisMapper diagnosisMapper;

    @Override
    @PreAuthorize("hasAnyRole('ADMIN', 'DOCTOR')")
    public List<DiagnosisResponseDTO> getDiagnoses(){
        return diagnosisMapper.toResponseDtoList(diagnosisRepository.findAll());
    }

    @Override
    @PreAuthorize("hasAnyRole('ADMIN', 'DOCTOR')")
    public DiagnosisResponseDTO getDiagnosisById(long id){
        return diagnosisMapper.toResponseDto(getDiagnosisEntityById(id));
    }

    @Override
    public Diagnosis getDiagnosisEntityById(long id){
        return diagnosisRepository.findById(id)
                .orElseThrow(() -> new DiagnosisNotFoundException("Diagnosis not found", id));
    }

    @Override
    public Set<Diagnosis> getDiagnosisEntitiesByIds(Set<Long> ids) {
        if (ids == null || ids.isEmpty()) {
            return Collections.emptySet();
        }
        return new HashSet<>(diagnosisRepository.findAllById(ids));
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public CreateDiagnosisDTO createDiagnosis(CreateDiagnosisDTO dto){
        Diagnosis diagnosis = diagnosisMapper.toEntity(dto);
        Diagnosis savedDiagnosis = diagnosisRepository.save(diagnosis);
        return dto;
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public UpdateDiagnosisDTO updateDiagnosis(long id, UpdateDiagnosisDTO dto){
        Diagnosis existingDiagnosis = diagnosisRepository.findById(id)
                .orElseThrow(() -> new DiagnosisNotFoundException("Diagnosis not found", id));
        diagnosisMapper.updateFromDto(dto, existingDiagnosis);
        Diagnosis updatedDiagnosis = diagnosisRepository.save(existingDiagnosis);
        return dto;
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteDiagnosis(long id){
        if (!diagnosisRepository.existsById(id)) {
            throw new DiagnosisNotFoundException("Diagnosis not found", id);
        }
        diagnosisRepository.deleteById(id);
    }
}
