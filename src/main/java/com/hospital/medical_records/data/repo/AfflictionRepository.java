package com.hospital.medical_records.data.repo;

import com.hospital.medical_records.data.entity.Affliction;
import com.hospital.medical_records.dto.affliction.AfflictionCountResponseDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AfflictionRepository extends JpaRepository<Affliction, Long> {
    @Query("SELECT NEW com.hospital.medical_records.dto.affliction.AfflictionCountResponseDTO(a.code, a.name, COUNT(v.id)) " +
            "FROM Visit v " +
            "JOIN v.diagnoses d " +
            "JOIN d.afflictions a " +
            "GROUP BY a.id, a.code, a.name " +
            "HAVING COUNT(v.id) >= ALL ( " +
            "    SELECT COUNT(v_sub.id) " +
            "    FROM Visit v_sub " +
            "    JOIN v_sub.diagnoses d_sub " +
            "    JOIN d_sub.afflictions a_sub " +
            "    GROUP BY a_sub.id " +
            ") ")
    List<AfflictionCountResponseDTO> findAfflictionCounts();
}