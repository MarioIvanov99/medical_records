package com.hospital.medical_records.data.repo;

import com.hospital.medical_records.data.entity.Diagnosis;
import com.hospital.medical_records.data.entity.Treatment;
import com.hospital.medical_records.data.entity.Visit;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface VisitRepository extends JpaRepository<Visit, Long> {
    List<Visit> findByPatient_IdOrderByVisitDateDesc(Long patientId);
    List<Visit> findByDoctor_IdOrderByVisitDateDesc(Long doctorId);

    @EntityGraph(attributePaths = {"treatments", "doctor"})
    List<Visit> findDistinctByPatient_IdAndTreatmentsIsNotEmptyOrderByVisitDateDesc(Long patientId);

    @EntityGraph(attributePaths = {"treatments", "patient"})
    List<Visit> findDistinctByDoctor_IdAndTreatmentsIsNotEmptyOrderByVisitDateDesc(Long doctorId);

    @EntityGraph(attributePaths = {"treatments", "patient"})
    List<Visit> findDistinctByDoctor_NameAndTreatmentsIsNotEmptyOrderByVisitDateDesc(String doctorName);

    @EntityGraph(attributePaths = {"diagnoses", "doctor"})
    List<Visit> findDistinctByPatient_IdAndDiagnosesIsNotEmptyOrderByVisitDateDesc(Long patientId);

    @EntityGraph(attributePaths = {"diagnoses", "patient"})
    List<Visit> findDistinctByDoctor_IdAndDiagnosesIsNotEmptyOrderByVisitDateDesc(Long doctorId);

    @EntityGraph(attributePaths = {"diagnoses", "patient"})
    List<Visit> findDistinctByDoctor_NameAndDiagnosesIsNotEmptyOrderByVisitDateDesc(String doctorName);

    @EntityGraph(attributePaths = {"diagnoses", "treatments", "patient", "doctor"})
    List<Visit> findByVisitDateBetweenOrderByVisitDateDesc(LocalDate start, LocalDate end);

    @EntityGraph(attributePaths = {"diagnoses", "treatments", "patient"})
    List<Visit> findDistinctByDoctor_IdAndVisitDateBetweenOrderByVisitDateDesc(Long id, LocalDate start, LocalDate end);

    @EntityGraph(attributePaths = {"diagnoses", "treatments", "patient"})
    List<Visit> findDistinctByDoctor_NameAndVisitDateBetweenOrderByVisitDateDesc(String name, LocalDate start, LocalDate end);
}
