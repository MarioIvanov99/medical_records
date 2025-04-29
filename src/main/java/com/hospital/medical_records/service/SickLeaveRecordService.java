package com.hospital.medical_records.service;

import com.hospital.medical_records.data.entity.SickLeaveRecord;
import com.hospital.medical_records.dto.sickLeaveRecord.*;

import java.util.List;

public interface SickLeaveRecordService {
    List<SickLeaveRecordResponseDTO> getSickLeaveRecords();
    SickLeaveRecordResponseDTO getSickLeaveRecordById(long id);

    List<SickLeaveRecordPatientResponseDTO> getSickLeaveRecordsByPatientId(long id);
    List<SickLeaveRecordDoctorResponseDTO> getSickLeaveRecordsByDoctorId(long id);
    List<SickLeaveRecordDoctorResponseDTO> getSickLeaveRecordsByDoctorName(String name);
    List<SickLeaveRecordMonthCountDTO> getSickLeaveRecordMonthCount();
    SickLeaveRecord getSickLeaveRecordEntityById(long id);
    CreateSickLeaveRecordDTO createSickLeaveRecord(CreateSickLeaveRecordDTO dto);
    UpdateSickLeaveRecordDTO updateSickLeaveRecord(long id, UpdateSickLeaveRecordDTO dto);
    void deleteSickLeaveRecord(long id);
}
