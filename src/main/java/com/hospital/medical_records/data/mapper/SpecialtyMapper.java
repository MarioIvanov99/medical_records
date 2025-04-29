package com.hospital.medical_records.data.mapper;

import com.hospital.medical_records.data.entity.Specialty;
import com.hospital.medical_records.dto.specialty.SpecialtyDTO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring")
public interface SpecialtyMapper {
    Specialty toEntity(SpecialtyDTO dto);
    Set<Specialty> toEntityList(Set<SpecialtyDTO> dtos);

    SpecialtyDTO toResponseDto(Specialty specialty);
    List<SpecialtyDTO> toResponseDtoList(List<Specialty> specialties);

    void updateSpecialty(SpecialtyDTO dto, @MappingTarget Specialty specialty);
}
