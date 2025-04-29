package com.hospital.medical_records.service;

import com.hospital.medical_records.data.entity.Doctor;
import com.hospital.medical_records.dto.doctor.CreateDoctorDTO;
import com.hospital.medical_records.dto.doctor.DoctorCountResponseDTO;
import com.hospital.medical_records.dto.doctor.DoctorResponseDTO;
import com.hospital.medical_records.dto.doctor.UpdateDoctorDTO;

import java.util.List;

public interface DoctorService {
    List<DoctorResponseDTO> getDoctors();
    DoctorResponseDTO getDoctorById(long id);
    Doctor getDoctorEntityById(long id);
    List<DoctorCountResponseDTO> getGpsByPatientCount();
    List<DoctorCountResponseDTO> getDoctorsByVisitCount();
    List<DoctorCountResponseDTO> getDoctorsWithMostSicKLeaveRecords();
    CreateDoctorDTO createDoctor(CreateDoctorDTO dto);
    UpdateDoctorDTO updateDoctor(long id, UpdateDoctorDTO dto);
    void deleteDoctor(long id);
}
