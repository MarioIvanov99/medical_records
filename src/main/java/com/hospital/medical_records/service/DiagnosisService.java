package com.hospital.medical_records.service;

import com.hospital.medical_records.data.entity.Diagnosis;
import com.hospital.medical_records.dto.diagnosis.CreateDiagnosisDTO;
import com.hospital.medical_records.dto.diagnosis.DiagnosisResponseDTO;
import com.hospital.medical_records.dto.diagnosis.UpdateDiagnosisDTO;

import java.util.List;
import java.util.Set;

public interface DiagnosisService {
    List<DiagnosisResponseDTO> getDiagnoses();
    DiagnosisResponseDTO getDiagnosisById(long id);
    Diagnosis getDiagnosisEntityById(long id);

    Set<Diagnosis> getDiagnosisEntitiesByIds(Set<Long> ids);
    CreateDiagnosisDTO createDiagnosis(CreateDiagnosisDTO dto);
    UpdateDiagnosisDTO updateDiagnosis(long id, UpdateDiagnosisDTO dto);
    void deleteDiagnosis(long id);
}
