package com.hospital.medical_records.data.mapper;

import com.hospital.medical_records.data.entity.Diagnosis;
import com.hospital.medical_records.data.entity.Treatment;
import com.hospital.medical_records.dto.diagnosis.CreateDiagnosisDTO;
import com.hospital.medical_records.dto.diagnosis.DiagnosisResponseDTO;
import com.hospital.medical_records.dto.diagnosis.UpdateDiagnosisDTO;
import com.hospital.medical_records.dto.treatment.TreatmentResponseDTO;
import com.hospital.medical_records.service.AfflictionService;
import org.mapstruct.*;

import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring", uses = {AfflictionMapper.class, AfflictionService.class})
public interface DiagnosisMapper {
    DiagnosisResponseDTO toResponseDto(Diagnosis diagnosis);
    List<DiagnosisResponseDTO> toResponseDtoList(List<Diagnosis> diagnoses);
    Set<DiagnosisResponseDTO> toResponseDtoSet(Set<Diagnosis> diagnoses);

    @Mapping(target = "afflictions", source = "afflictionIds")
    Diagnosis toEntity(CreateDiagnosisDTO createDto);

    @Mapping(target = "afflictions", source = "afflictionIds")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateFromDto(UpdateDiagnosisDTO updateDto, @MappingTarget Diagnosis diagnosis);
}
