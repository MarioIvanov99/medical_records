package com.hospital.medical_records.data.mapper;

import com.hospital.medical_records.data.entity.Affliction;
import com.hospital.medical_records.data.entity.Treatment;
import com.hospital.medical_records.dto.affliction.AfflictionResponseDTO;
import com.hospital.medical_records.dto.treatment.TreatmentResponseDTO;
import com.hospital.medical_records.dto.treatment.CreateTreatmentDTO;
import com.hospital.medical_records.dto.treatment.UpdateTreatmentDTO;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring")
public interface TreatmentMapper {
    Treatment toEntity(CreateTreatmentDTO dto);

    TreatmentResponseDTO toResponseDto(Treatment treatment);
    List<TreatmentResponseDTO> toResponseDtoList(List<Treatment> treatments);
    Set<TreatmentResponseDTO> toResponseDtoSet(Set<Treatment> treatments);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateTreatmentDTO(UpdateTreatmentDTO dto, @MappingTarget Treatment treatment);
}
