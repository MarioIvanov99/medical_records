package com.hospital.medical_records.data.repo;

import com.hospital.medical_records.data.entity.Doctor;
import com.hospital.medical_records.dto.doctor.DoctorCountResponseDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {
    @Query("SELECT NEW com.hospital.medical_records.dto.doctor.DoctorCountResponseDTO(d.name, COUNT(p.id)) " +
            "FROM Doctor d " +
            "JOIN d.patients p " +
            "WHERE d.gp = true " +
            "GROUP BY d.id, d.name " +
            "ORDER BY COUNT(p.id) DESC")
    List<DoctorCountResponseDTO> findGpPatientCounts();

    @Query("SELECT NEW com.hospital.medical_records.dto.doctor.DoctorCountResponseDTO(d.name, COUNT(v.id)) " +
            "FROM Doctor d " +
            "JOIN d.visits v " +
            "GROUP BY d.id, d.name " +
            "ORDER BY COUNT(v.id) DESC")
    List<DoctorCountResponseDTO> findDoctorVisitCounts();

    @Query("SELECT NEW com.hospital.medical_records.dto.doctor.DoctorCountResponseDTO(d.name, COUNT(slr.id)) " +
            "FROM Doctor d " +
            "JOIN d.visits v " +
            "JOIN v.sickLeaveRecord slr " +
            "GROUP BY d.id, d.name " +
            "HAVING COUNT(slr.id) >= ALL ( " +
            "    SELECT COUNT(slr_sub.id) " +
            "    FROM Doctor d_sub " +
            "    JOIN d_sub.visits v_sub " +
            "    JOIN v_sub.sickLeaveRecord slr_sub " +
            "    GROUP BY d_sub.id " +
            ") " +
            "ORDER BY d.name ASC")
    List<DoctorCountResponseDTO> findDoctorsWithMostSickLeaveRecords();
}
