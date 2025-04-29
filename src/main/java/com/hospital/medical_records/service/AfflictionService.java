package com.hospital.medical_records.service;

import com.hospital.medical_records.data.entity.Affliction;
import com.hospital.medical_records.dto.affliction.AfflictionCountResponseDTO;
import com.hospital.medical_records.dto.affliction.AfflictionResponseDTO;
import com.hospital.medical_records.dto.affliction.CreateAfflictionDTO;
import com.hospital.medical_records.dto.affliction.PatchAfflictionDTO;

import java.util.List;
import java.util.Set;

public interface AfflictionService {
    List<AfflictionResponseDTO> getAfflictions();
    AfflictionResponseDTO getAfflictionById(long id);
    List<AfflictionCountResponseDTO> getAfflictionsByCounts();
    CreateAfflictionDTO createAffliction(CreateAfflictionDTO dto);
    Affliction updateAffliction(long id, PatchAfflictionDTO dto);
    void deleteAffliction(long id);
    Affliction getAfflictionByEntityById(Long id);
    Set<Affliction> getAfflictionEntitiesByIds(Set<Long> ids);
}
