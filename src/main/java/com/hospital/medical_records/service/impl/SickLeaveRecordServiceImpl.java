package com.hospital.medical_records.service.impl;

import com.hospital.medical_records.data.entity.SickLeaveRecord;
import com.hospital.medical_records.data.mapper.SickLeaveRecordMapper;
import com.hospital.medical_records.data.repo.SickLeaveRecordRepository;
import com.hospital.medical_records.dto.sickLeaveRecord.*;

import com.hospital.medical_records.exception.SickLeaveRecordNotFoundException;
import com.hospital.medical_records.service.SickLeaveRecordService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SickLeaveRecordServiceImpl implements SickLeaveRecordService {
    private final SickLeaveRecordRepository sickLeaveRecordRepository;
    private final SickLeaveRecordMapper sickLeaveRecordMapper;

    @Override
    @PreAuthorize("hasAnyRole('ADMIN', 'DOCTOR')")
    public List<SickLeaveRecordResponseDTO> getSickLeaveRecords(){
        return sickLeaveRecordMapper.toResponseDtoList(sickLeaveRecordRepository.findAll());
    }

    @Override
    @PreAuthorize("hasAnyRole('ADMIN', 'DOCTOR')")
    public SickLeaveRecordResponseDTO getSickLeaveRecordById(long id){
        return sickLeaveRecordMapper.toResponseDto(sickLeaveRecordRepository.findById(id)
                .orElseThrow(() -> new SickLeaveRecordNotFoundException("Sick Leave Record not found", id)));
    }

    @Override
    @PreAuthorize("hasAnyRole('ADMIN', 'DOCTOR') or (isAuthenticated() and #id == authentication.principal.getId())")
    public List<SickLeaveRecordPatientResponseDTO> getSickLeaveRecordsByPatientId(long id){
        return sickLeaveRecordMapper.toPatientResponseDtoList(sickLeaveRecordRepository.findByVisit_Patient_IdOrderByVisit_VisitDateDesc(id));
    }

    @Override
    @PreAuthorize("hasAnyRole('ADMIN', 'DOCTOR')")
    public List<SickLeaveRecordDoctorResponseDTO> getSickLeaveRecordsByDoctorId(long id){
        return sickLeaveRecordMapper.toDoctorResponseDtoList(sickLeaveRecordRepository.findByVisit_Doctor_IdOrderByVisit_VisitDateDesc(id));
    }

    @Override
    @PreAuthorize("hasAnyRole('ADMIN', 'DOCTOR')")
    public List<SickLeaveRecordDoctorResponseDTO> getSickLeaveRecordsByDoctorName(String name){
        return sickLeaveRecordMapper.toDoctorResponseDtoList(sickLeaveRecordRepository.findByVisit_Doctor_NameOrderByVisit_VisitDateDesc(name));
    }

    @Override
    @PreAuthorize("hasAnyRole('ADMIN', 'DOCTOR')")
    public List<SickLeaveRecordMonthCountDTO> getSickLeaveRecordMonthCount() {
        return sickLeaveRecordRepository.findMonthWithHighestSickLeavesDirect();
    }

    @Override
    public SickLeaveRecord getSickLeaveRecordEntityById(long id){
        return sickLeaveRecordRepository.findById(id)
                .orElseThrow(() -> new SickLeaveRecordNotFoundException("Sick Leave Record not found", id));
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public CreateSickLeaveRecordDTO createSickLeaveRecord(CreateSickLeaveRecordDTO dto){
        SickLeaveRecord sickLeaveRecord = sickLeaveRecordMapper.toEntity(dto);
        SickLeaveRecord savedSickLeaveRecord = sickLeaveRecordRepository.save(sickLeaveRecord);
        return dto;
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public UpdateSickLeaveRecordDTO updateSickLeaveRecord(long id, UpdateSickLeaveRecordDTO dto){
        SickLeaveRecord existingSickLeaveRecord = sickLeaveRecordRepository.findById(id)
                .orElseThrow(() -> new SickLeaveRecordNotFoundException("Sick Leave Record not found", id));
        sickLeaveRecordMapper.updateFromDto(dto, existingSickLeaveRecord);
        SickLeaveRecord updatedSickLeaveRecord = sickLeaveRecordRepository.save(existingSickLeaveRecord);
        return dto;
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteSickLeaveRecord(long id){
        if(!sickLeaveRecordRepository.existsById(id)){
            throw new SickLeaveRecordNotFoundException("Sick Leave Record not found", id);
        }
        sickLeaveRecordRepository.deleteById(id);
    }
}
