package com.hospital.medical_records.service;

import com.hospital.medical_records.data.entity.Specialty;
import com.hospital.medical_records.dto.specialty.SpecialtyDTO;
import org.springframework.boot.autoconfigure.rsocket.RSocketProperties;

import java.util.List;

public interface SpecialtyService {
    List<SpecialtyDTO> getSpecialties();
    SpecialtyDTO getSpecialtyById(long id);
    SpecialtyDTO createSpecialty(SpecialtyDTO dto);
    Specialty updateSpecialty(long id, SpecialtyDTO dto);
    void deleteSpecialty(long id);
    Specialty getDetailedSpecialtyById(long id);
}
