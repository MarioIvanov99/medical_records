package com.hospital.medical_records.service.impl;

import com.hospital.medical_records.data.entity.Affliction;
import com.hospital.medical_records.data.mapper.AfflictionMapper;
import com.hospital.medical_records.data.repo.AfflictionRepository;
import com.hospital.medical_records.dto.affliction.AfflictionCountResponseDTO;
import com.hospital.medical_records.exception.AfflictionNotFoundException;
import com.hospital.medical_records.service.AfflictionService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import com.hospital.medical_records.dto.affliction.AfflictionResponseDTO;
import com.hospital.medical_records.dto.affliction.CreateAfflictionDTO;
import com.hospital.medical_records.dto.affliction.PatchAfflictionDTO;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class AfflictionServiceImpl implements AfflictionService {
    private final AfflictionRepository afflictionRepository;
    private final AfflictionMapper afflictionMapper;

    @Override
    @PreAuthorize("hasAnyRole('ADMIN', 'DOCTOR')")
    public List<AfflictionResponseDTO> getAfflictions() {
        return afflictionMapper.toResponseDtoList(afflictionRepository.findAll());
    }

    @Override
    @PreAuthorize("hasAnyRole('ADMIN', 'DOCTOR')")
    public AfflictionResponseDTO getAfflictionById(long id) {
        Affliction affliction = afflictionRepository.findById(id)
                .orElseThrow(() -> new AfflictionNotFoundException("Affliction not found", id));
        return afflictionMapper.toResponseDto(affliction);
    }

    @Override
    @PreAuthorize("hasAnyRole('ADMIN', 'DOCTOR')")
    public List<AfflictionCountResponseDTO> getAfflictionsByCounts() {
        return afflictionRepository.findAfflictionCounts();
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public CreateAfflictionDTO createAffliction(CreateAfflictionDTO dto) {
        Affliction affliction = afflictionMapper.toEntity(dto);
        Affliction saved = afflictionRepository.save(affliction);
        return dto;
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public Affliction updateAffliction(long id, PatchAfflictionDTO dto) {
        Affliction existing = afflictionRepository.findById(id)
                .orElseThrow(() -> new AfflictionNotFoundException("Affliction not found", id));

        afflictionMapper.patchAffliction(dto, existing);
        return afflictionRepository.save(existing);
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteAffliction(long id) {
        if (!afflictionRepository.existsById(id)) {
            throw new AfflictionNotFoundException("Affliction not found", id);
        }
        afflictionRepository.deleteById(id);
    }

    @Override
    public Affliction getAfflictionByEntityById(Long id) {
        if (id == null) {
            return null;
        }
        return afflictionRepository.findById(id)
                .orElseThrow(() -> new AfflictionNotFoundException("Affliction not found", id));
    }

    @Override
    public Set<Affliction> getAfflictionEntitiesByIds(Set<Long> ids) {
        if (ids == null || ids.isEmpty()) {
            return Collections.emptySet();
        }
        return new HashSet<>(afflictionRepository.findAllById(ids));
    }
}
