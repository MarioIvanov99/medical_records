package com.hospital.medical_records.data.mapper;

import com.hospital.medical_records.data.entity.Visit;
import com.hospital.medical_records.dto.visit.*;
import com.hospital.medical_records.service.DiagnosisService;
import com.hospital.medical_records.service.DoctorService;
import com.hospital.medical_records.service.PatientService;
import com.hospital.medical_records.service.TreatmentService;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring",
        uses = {
                PatientService.class,
                DoctorService.class,
                TreatmentService.class,
                DiagnosisService.class,
                TreatmentMapper.class,
                DiagnosisMapper.class
        })
public interface VisitMapper {

    @Mapping(source = "patient.name", target = "patientName")
    @Mapping(source = "doctor.name", target = "doctorName")
    @Mapping(source = "sickLeaveRecord.id", target = "sickLeaveRecordId")
    @Mapping(target = "treatments")
    @Mapping(target = "diagnoses")
    VisitResponseDTO toResponseDto(Visit visit);

    @Mapping(source = "doctor.name", target = "doctorName")
    @Mapping(source = "sickLeaveRecord.id", target = "sickLeaveRecordId")
    @Mapping(target = "treatments")
    @Mapping(target = "diagnoses")
    VisitPatientResponseDTO toPatientResponseDto(Visit visit);

    @Mapping(source = "doctor.name", target = "doctorName")
    @Mapping(target = "treatments")
    VisitPatientTreatmentResponseDTO toPatientTreatmentResponseDto(Visit visit);

    @Mapping(source = "doctor.name", target = "doctorName")
    @Mapping(target = "diagnoses")
    VisitPatientDiagnosisResponseDTO toPatientDiagnosisResponseDto(Visit visit);

    @Mapping(source = "patient.name", target = "patientName")
    @Mapping(source = "sickLeaveRecord.id", target = "sickLeaveRecordId")
    @Mapping(target = "treatments")
    @Mapping(target = "diagnoses")
    VisitDoctorResponseDTO toDoctorResponseDto(Visit visit);

    @Mapping(source = "patient.name", target = "patientName")
    @Mapping(target = "treatments")
    VisitDoctorTreatmentResponseDTO toDoctorTreatmentResponseDto(Visit visit);

    @Mapping(source = "patient.name", target = "patientName")
    @Mapping(target = "diagnoses")
    VisitDoctorDiagnosisResponseDTO toDoctorDiagnosisResponseDto(Visit visit);

    List<VisitResponseDTO> toResponseDtoList(List<Visit> visits);
    List<VisitPatientResponseDTO> toPatientResponseDtoList(List<Visit> visits);
    List<VisitPatientTreatmentResponseDTO> toPatientTreatmentResponseDtoList(List<Visit> visits);
    List<VisitPatientDiagnosisResponseDTO> toPatientDiagnosisResponseDtoList(List<Visit> visits);
    List<VisitDoctorResponseDTO> toDoctorResponseDtoList(List<Visit> visits);
    List<VisitDoctorTreatmentResponseDTO> toDoctorTreatmentResponseDtoList(List<Visit> visits);
    List<VisitDoctorDiagnosisResponseDTO> toDoctorDiagnosisResponseDtoList(List<Visit> visits);

    @Mapping(source = "patientId", target = "patient")
    @Mapping(source = "doctorId", target = "doctor")
    @Mapping(source = "treatmentIds", target = "treatments")
    @Mapping(source = "diagnosisIds", target = "diagnoses")
    Visit toEntity(CreateVisitDTO createDto);

    @Mapping(source = "treatmentIds", target = "treatments")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateFromDto(UpdateVisitTreatmentDTO updateDto, @MappingTarget Visit visit);

    @Mapping(source = "diagnosisIds", target = "diagnoses")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateFromDto(UpdateVisitDiagnosisDTO updateDto, @MappingTarget Visit visit);
}
