package com.hospital.medical_records.service;

import com.hospital.medical_records.data.entity.Treatment;
import com.hospital.medical_records.data.entity.Treatment;
import com.hospital.medical_records.dto.treatment.TreatmentResponseDTO;
import com.hospital.medical_records.dto.treatment.CreateTreatmentDTO;
import com.hospital.medical_records.dto.treatment.UpdateTreatmentDTO;

import java.util.List;
import java.util.Set;

public interface TreatmentService {
    List<TreatmentResponseDTO> getTreatments();
    TreatmentResponseDTO getTreatmentById(long id);
    Treatment getTreatmentEntityById(long id);

    Set<Treatment> getTreatmentEntitiesByIds(Set<Long> ids);
    CreateTreatmentDTO createTreatment(CreateTreatmentDTO dto);
    Treatment updateTreatment(long id, UpdateTreatmentDTO dto);
    void deleteTreatment(long id);
}
