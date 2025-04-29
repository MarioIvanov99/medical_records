package com.hospital.medical_records.data.mapper;

import com.hospital.medical_records.data.entity.Affliction;
import com.hospital.medical_records.dto.affliction.AfflictionResponseDTO;
import com.hospital.medical_records.dto.affliction.CreateAfflictionDTO;
import com.hospital.medical_records.dto.affliction.PatchAfflictionDTO;

import org.mapstruct.*;

import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring")
public interface AfflictionMapper {
    Affliction toEntity(CreateAfflictionDTO dto);

    AfflictionResponseDTO toResponseDto(Affliction affliction);
    List<AfflictionResponseDTO> toResponseDtoList(List<Affliction> afflictions);
    Set<AfflictionResponseDTO> toResponseDtoSet(Set<Affliction> afflictions);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void patchAffliction(PatchAfflictionDTO dto, @MappingTarget Affliction affliction);
}
