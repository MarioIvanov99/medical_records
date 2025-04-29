package com.hospital.medical_records.service.impl;

import com.hospital.medical_records.data.entity.Visit;
import com.hospital.medical_records.data.mapper.VisitMapper;
import com.hospital.medical_records.data.repo.VisitRepository;
import com.hospital.medical_records.dto.visit.*;
import com.hospital.medical_records.exception.InvalidDateRangeException;
import com.hospital.medical_records.exception.VisitNotFoundException;
import com.hospital.medical_records.service.VisitService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class VisitServiceImpl implements VisitService {
    private final VisitRepository visitRepository;
    private final VisitMapper visitMapper;

    @Override
    @PreAuthorize("hasAnyRole('ADMIN', 'DOCTOR')")
    public List<VisitResponseDTO> getVisits(){
        return visitMapper.toResponseDtoList(visitRepository.findAll());
    }

    @Override
    @PreAuthorize("hasAnyRole('ADMIN', 'DOCTOR') or (isAuthenticated() and #id == authentication.principal.getId())")
    public List<VisitPatientResponseDTO> getVisitsByPatientId(long id){
        return visitMapper.toPatientResponseDtoList(visitRepository.findByPatient_IdOrderByVisitDateDesc(id));
    }

    @Override
    @PreAuthorize("hasAnyRole('ADMIN', 'DOCTOR') or (isAuthenticated() and #id == authentication.principal.getId())")
    public List<VisitPatientTreatmentResponseDTO> getTreatmentsByPatientId(long id) {
        return visitMapper.toPatientTreatmentResponseDtoList(visitRepository.findDistinctByPatient_IdAndTreatmentsIsNotEmptyOrderByVisitDateDesc(id));
    }

    @Override
    @PreAuthorize("hasAnyRole('ADMIN', 'DOCTOR') or (isAuthenticated() and #id == authentication.principal.getId())")
    public List<VisitPatientDiagnosisResponseDTO> getDiagnosesByPatientId(long id) {
        return visitMapper.toPatientDiagnosisResponseDtoList(visitRepository.findDistinctByPatient_IdAndDiagnosesIsNotEmptyOrderByVisitDateDesc(id));
    }

    @Override
    @PreAuthorize("hasAnyRole('ADMIN', 'DOCTOR')")
    public List<VisitDoctorResponseDTO> getVisitsByDoctorId(long id){
        return visitMapper.toDoctorResponseDtoList(visitRepository.findByDoctor_IdOrderByVisitDateDesc(id));
    }

    @Override
    @PreAuthorize("hasAnyRole('ADMIN', 'DOCTOR')")
    public List<VisitDoctorTreatmentResponseDTO> getTreatmentsByDoctorId(long id) {
        return visitMapper.toDoctorTreatmentResponseDtoList(visitRepository.findDistinctByDoctor_IdAndTreatmentsIsNotEmptyOrderByVisitDateDesc(id));
    }

    @Override
    @PreAuthorize("hasAnyRole('ADMIN', 'DOCTOR')")
    public List<VisitDoctorTreatmentResponseDTO> getTreatmentsByDoctorName(String name) {
        return visitMapper.toDoctorTreatmentResponseDtoList(visitRepository.findDistinctByDoctor_NameAndTreatmentsIsNotEmptyOrderByVisitDateDesc(name));
    }

    @Override
    @PreAuthorize("hasAnyRole('ADMIN', 'DOCTOR')")
    public List<VisitDoctorDiagnosisResponseDTO> getDiagnosesByDoctorId(long id) {
        return visitMapper.toDoctorDiagnosisResponseDtoList(visitRepository.findDistinctByDoctor_IdAndDiagnosesIsNotEmptyOrderByVisitDateDesc(id));
    }

    @Override
    @PreAuthorize("hasAnyRole('ADMIN', 'DOCTOR')")
    public List<VisitDoctorDiagnosisResponseDTO> getDiagnosesByDoctorName(String name) {
        return visitMapper.toDoctorDiagnosisResponseDtoList(visitRepository.findDistinctByDoctor_NameAndDiagnosesIsNotEmptyOrderByVisitDateDesc(name));
    }

    @Override
    @PreAuthorize("hasAnyRole('ADMIN', 'DOCTOR')")
    public VisitResponseDTO getVisitById(long id){
        return visitMapper.toResponseDto(visitRepository.findById(id)
                .orElseThrow(() -> new VisitNotFoundException("Visit not found", id)));
    }

    @Override
    public Visit getVisitEntityById(long id){
        return visitRepository.findById(id)
                .orElseThrow(() -> new VisitNotFoundException("Visit not found", id));
    }

    @Override
    @PreAuthorize("hasAnyRole('ADMIN', 'DOCTOR')")
    public List<VisitResponseDTO> getVisitsInPeriod(LocalDate startDate, LocalDate endDate){
        validateDateRange(startDate, endDate);
        return visitMapper.toResponseDtoList(visitRepository.findByVisitDateBetweenOrderByVisitDateDesc(startDate, endDate));
    }

    @Override
    @PreAuthorize("hasAnyRole('ADMIN', 'DOCTOR')")
    public List<VisitDoctorResponseDTO> getVisitsInPeriodByDoctorId(long id, LocalDate startDate, LocalDate endDate){
        validateDateRange(startDate, endDate);
        return visitMapper.toDoctorResponseDtoList(visitRepository.findDistinctByDoctor_IdAndVisitDateBetweenOrderByVisitDateDesc(id, startDate, endDate));
    }

    @Override
    @PreAuthorize("hasAnyRole('ADMIN', 'DOCTOR')")
    public List<VisitDoctorResponseDTO> getVisitsInPeriodByDoctorName(String name, LocalDate startDate, LocalDate endDate){
        validateDateRange(startDate, endDate);
        return visitMapper.toDoctorResponseDtoList(visitRepository.findDistinctByDoctor_NameAndVisitDateBetweenOrderByVisitDateDesc(name, startDate, endDate));
    }

    @Override
    @PreAuthorize("hasAnyRole('ADMIN', 'DOCTOR')")
    public CreateVisitDTO createVisit(CreateVisitDTO dto){
        Visit visit = visitMapper.toEntity(dto);
        Visit savedVisit = visitRepository.save(visit);
        return dto;
    }

    @Override
    @PreAuthorize("hasRole('ADMIN') or @visitSecurityService.isDoctorOwnerOfVisit(#id)")
    public UpdateVisitTreatmentDTO updateVisit(long id, UpdateVisitTreatmentDTO dto){
        Visit existingVisit = visitRepository.findById(id)
                .orElseThrow(() -> new VisitNotFoundException("Visit not found", id));
        visitMapper.updateFromDto(dto, existingVisit);
        Visit updatedVisit = visitRepository.save(existingVisit);
        return dto;
    }

    @Override
    @PreAuthorize("hasRole('ADMIN') or @visitSecurityService.isDoctorOwnerOfVisit(#id)")
    public UpdateVisitDiagnosisDTO updateVisit(long id, UpdateVisitDiagnosisDTO dto){
        Visit existingVisit = visitRepository.findById(id)
                .orElseThrow(() -> new VisitNotFoundException("Visit not found", id));
        visitMapper.updateFromDto(dto, existingVisit);
        Visit updatedVisit = visitRepository.save(existingVisit);
        return dto;
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteVisit(long id){
        if (!visitRepository.existsById(id)){
            throw new VisitNotFoundException("Visit not found", id);
        }
        visitRepository.deleteById(id);
    }

    private void validateDateRange(LocalDate startDate, LocalDate endDate) {
        if (startDate.isAfter(endDate)) {
            throw new InvalidDateRangeException("Invalid date range: " + startDate + " to " + endDate + ". Start date must be before end date.");
        }
    }
}
