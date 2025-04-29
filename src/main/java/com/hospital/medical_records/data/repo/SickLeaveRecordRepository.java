package com.hospital.medical_records.data.repo;

import com.hospital.medical_records.data.entity.SickLeaveRecord;
import com.hospital.medical_records.dto.sickLeaveRecord.SickLeaveRecordMonthCountDTO;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SickLeaveRecordRepository extends JpaRepository<SickLeaveRecord, Long> {
    @EntityGraph(attributePaths = {"visit", "visit.doctor"})
    List<SickLeaveRecord> findByVisit_Patient_IdOrderByVisit_VisitDateDesc(Long patientId);

    @EntityGraph(attributePaths = {"visit", "visit.patient"})
    List<SickLeaveRecord> findByVisit_Doctor_IdOrderByVisit_VisitDateDesc(Long doctorId);

    @EntityGraph(attributePaths = {"visit", "visit.patient"})
    List<SickLeaveRecord> findByVisit_Doctor_NameOrderByVisit_VisitDateDesc(String doctorName);

    @Query("SELECT NEW com.hospital.medical_records.dto.sickLeaveRecord.SickLeaveRecordMonthCountDTO(YEAR(slr.startDate), MONTH(slr.startDate), COUNT(slr.id)) " +
            "FROM SickLeaveRecord slr " +
            "WHERE slr.startDate IS NOT NULL " +
            "GROUP BY YEAR(slr.startDate), MONTH(slr.startDate) " +
            "HAVING COUNT(slr.id) >= ALL ( " +
            "    SELECT COUNT(slr_sub.id) " +
            "    FROM SickLeaveRecord slr_sub " +
            "    WHERE slr_sub.startDate IS NOT NULL " +
            "    GROUP BY YEAR(slr_sub.startDate), MONTH(slr_sub.startDate) " +
            ") " +
            "ORDER BY YEAR(slr.startDate) DESC, MONTH(slr.startDate) DESC")
    List<SickLeaveRecordMonthCountDTO> findMonthWithHighestSickLeavesDirect();
}
