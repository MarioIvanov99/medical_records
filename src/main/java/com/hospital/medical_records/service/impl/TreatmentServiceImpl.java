package com.hospital.medical_records.service.impl;

import com.hospital.medical_records.data.entity.Treatment;
import com.hospital.medical_records.data.mapper.TreatmentMapper;
import com.hospital.medical_records.data.repo.TreatmentRepository;
import com.hospital.medical_records.dto.treatment.TreatmentResponseDTO;
import com.hospital.medical_records.dto.treatment.CreateTreatmentDTO;
import com.hospital.medical_records.dto.treatment.UpdateTreatmentDTO;
import com.hospital.medical_records.exception.TreatmentNotFoundException;
import com.hospital.medical_records.service.TreatmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class TreatmentServiceImpl implements TreatmentService {
    private final TreatmentRepository treatmentRepository;
    private final TreatmentMapper treatmentMapper;

    @Override
    @PreAuthorize("hasAnyRole('ADMIN', 'DOCTOR')")
    public List<TreatmentResponseDTO> getTreatments() {
        return treatmentMapper.toResponseDtoList(treatmentRepository.findAll());
    }

    @Override
    @PreAuthorize("hasAnyRole('ADMIN', 'DOCTOR')")
    public TreatmentResponseDTO getTreatmentById(long id) {
        Treatment treatment = treatmentRepository.findById(id)
                .orElseThrow(() -> new TreatmentNotFoundException("Treatment not found", id));
        return treatmentMapper.toResponseDto(treatment);
    }

    @Override
    public Treatment getTreatmentEntityById(long id){
        return treatmentRepository.findById(id)
                .orElseThrow(() -> new TreatmentNotFoundException("Treatment not found", id));
    }

    @Override
    public Set<Treatment> getTreatmentEntitiesByIds(Set<Long> ids) {
        if (ids == null || ids.isEmpty()) {
            return Collections.emptySet();
        }
        return new HashSet<>(treatmentRepository.findAllById(ids));
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public CreateTreatmentDTO createTreatment(CreateTreatmentDTO dto) {
        Treatment treatment = treatmentMapper.toEntity(dto);
        Treatment saved = treatmentRepository.save(treatment);
        return dto;
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public Treatment updateTreatment(long id, UpdateTreatmentDTO dto) {
        Treatment existing = treatmentRepository.findById(id)
                .orElseThrow(() -> new TreatmentNotFoundException("Treatment not found", id));

        treatmentMapper.updateTreatmentDTO(dto, existing);
        return treatmentRepository.save(existing);
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteTreatment(long id) {
        if (!treatmentRepository.existsById(id)) {
            throw new TreatmentNotFoundException("Treatment not found", id);
        }
        treatmentRepository.deleteById(id);
    }
}
