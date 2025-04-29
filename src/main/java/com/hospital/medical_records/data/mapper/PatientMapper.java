package com.hospital.medical_records.data.mapper;

import com.hospital.medical_records.data.entity.Patient;
import com.hospital.medical_records.dto.patient.*;
import com.hospital.medical_records.service.DoctorService;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", uses = {DoctorService.class})
public interface PatientMapper {
    @Mapping(source = "gpId", target = "gp")
    Patient toEntity(CreatePatientDTO createPatientDTO);

    @Mapping(source = "gp.name", target = "gpName")
    PatientResponseDTO toResponseDto(Patient patient);

    PatientNameCodeDTO toNameCodeDto(Patient patient);

    @Mapping(source = "gp.name", target = "gpName")
    PatientDetailedResponseDTO toDetailedResponseDto(Patient patient);

    List<PatientResponseDTO> toResponseDtoList(List<Patient> patients);
    List<PatientNameCodeDTO> toNameCodeDtoList(List<Patient> patients);
    List<PatientDetailedResponseDTO> toDetailedResponseDtoList(List<Patient> patients);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(source = "gpId", target = "gp")
    void updatePatient(UpdatePatientDTO dto, @MappingTarget Patient patient);
}
