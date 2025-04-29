package com.hospital.medical_records.data.mapper;

import com.hospital.medical_records.data.entity.SickLeaveRecord;
import com.hospital.medical_records.dto.sickLeaveRecord.*;
import com.hospital.medical_records.service.VisitService;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", uses = {VisitService.class})
public interface SickLeaveRecordMapper {
    @Mapping(source = "visit.visitDate", target = "visitDate")
    SickLeaveRecordResponseDTO toResponseDto(SickLeaveRecord record);

    @Mapping(source = "visit.doctor.name", target = "doctorName")
    @Mapping(source = "visit.visitDate", target = "visitDate")
    SickLeaveRecordPatientResponseDTO toPatientResponseDto(SickLeaveRecord record);

    @Mapping(source = "visit.patient.name", target = "patientName")
    @Mapping(source = "visit.visitDate", target = "visitDate")
    SickLeaveRecordDoctorResponseDTO toDoctorResponseDto(SickLeaveRecord record);

    List<SickLeaveRecordResponseDTO> toResponseDtoList(List<SickLeaveRecord> records);
    List<SickLeaveRecordPatientResponseDTO> toPatientResponseDtoList(List<SickLeaveRecord> records);
    List<SickLeaveRecordDoctorResponseDTO> toDoctorResponseDtoList(List<SickLeaveRecord> records);

    @Mapping(source = "visitId", target = "visit")
    SickLeaveRecord toEntity(CreateSickLeaveRecordDTO createDto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateFromDto(UpdateSickLeaveRecordDTO updateDto, @MappingTarget SickLeaveRecord record);
}
