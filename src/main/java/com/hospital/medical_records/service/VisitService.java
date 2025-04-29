package com.hospital.medical_records.service;

import com.hospital.medical_records.data.entity.Visit;
import com.hospital.medical_records.dto.visit.*;

import java.time.LocalDate;
import java.util.List;

public interface VisitService {
    List<VisitResponseDTO> getVisits();
    List<VisitPatientResponseDTO> getVisitsByPatientId(long id);
    List<VisitPatientTreatmentResponseDTO> getTreatmentsByPatientId(long id);
    List<VisitPatientDiagnosisResponseDTO> getDiagnosesByPatientId(long id);
    List<VisitDoctorResponseDTO> getVisitsByDoctorId(long id);
    List<VisitDoctorTreatmentResponseDTO> getTreatmentsByDoctorId(long id);
    List<VisitDoctorTreatmentResponseDTO> getTreatmentsByDoctorName(String name);
    List<VisitDoctorDiagnosisResponseDTO> getDiagnosesByDoctorId(long id);
    List<VisitDoctorDiagnosisResponseDTO> getDiagnosesByDoctorName(String name);
    VisitResponseDTO getVisitById(long id);
    Visit getVisitEntityById(long id);
    List<VisitResponseDTO> getVisitsInPeriod(LocalDate startDate, LocalDate endDate);
    List<VisitDoctorResponseDTO> getVisitsInPeriodByDoctorId(long id, LocalDate startDate, LocalDate endDate);
    List<VisitDoctorResponseDTO> getVisitsInPeriodByDoctorName(String name, LocalDate startDate, LocalDate endDate);
    CreateVisitDTO createVisit(CreateVisitDTO dto);
    UpdateVisitTreatmentDTO updateVisit(long id, UpdateVisitTreatmentDTO dto);
    UpdateVisitDiagnosisDTO updateVisit(long id, UpdateVisitDiagnosisDTO dto);
    void deleteVisit(long id);
}
