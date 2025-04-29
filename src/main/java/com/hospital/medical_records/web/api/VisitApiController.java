package com.hospital.medical_records.web.api;

import com.hospital.medical_records.dto.visit.*;
import com.hospital.medical_records.service.VisitSecurityService;
import com.hospital.medical_records.service.VisitService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/visits")
@Validated
public class VisitApiController {
    private final VisitService visitService;
    private final VisitSecurityService visitSecurityService;

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'DOCTOR')")
    public ResponseEntity<List<VisitResponseDTO>> getVisits() {
        return ResponseEntity.ok(this.visitService.getVisits());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'DOCTOR')")
    public ResponseEntity<VisitResponseDTO> getVisitById(@PathVariable @Min(1) long id) {
        return ResponseEntity.ok(this.visitService.getVisitById(id));
    }

    @GetMapping("/by-patient/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'DOCTOR') or (isAuthenticated() and #id == authentication.principal.getId())")
    public ResponseEntity<List<VisitPatientResponseDTO>> getVisitsByPatientId(@PathVariable @Min(1) long id) {
        return ResponseEntity.ok(this.visitService.getVisitsByPatientId(id));
    }

    @GetMapping("/by-patient/treatments/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'DOCTOR') or (isAuthenticated() and #id == authentication.principal.getId())")
    public ResponseEntity<List<VisitPatientTreatmentResponseDTO>> getTreatmentsByPatientId(@PathVariable @Min(1) long id) {
        return ResponseEntity.ok(this.visitService.getTreatmentsByPatientId(id));
    }

    @GetMapping("/by-patient/diagnoses/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'DOCTOR') or #id == authentication.principal.id")
    public ResponseEntity<List<VisitPatientDiagnosisResponseDTO>> getDiagnosesByPatientId(@PathVariable @Min(1) long id) {
        return ResponseEntity.ok(this.visitService.getDiagnosesByPatientId(id));
    }

    @GetMapping("/by-doctor/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'DOCTOR')")
    public ResponseEntity<List<VisitDoctorResponseDTO>> getVisitsByDoctorId(@PathVariable @Min(1) long id) {
        return ResponseEntity.ok(this.visitService.getVisitsByDoctorId(id));
    }

    @GetMapping("/treatments/by-doctor/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'DOCTOR')")
    public ResponseEntity<List<VisitDoctorTreatmentResponseDTO>> getTreatmentsByDoctorId(@PathVariable @Min(1) long id) {
        return ResponseEntity.ok(this.visitService.getTreatmentsByDoctorId(id));
    }

    @GetMapping("/treatments/by-doctor-name")
    @PreAuthorize("hasAnyRole('ADMIN', 'DOCTOR')")
    public ResponseEntity<List<VisitDoctorTreatmentResponseDTO>> getTreatmentsByDoctorName(
            @RequestParam
            @NotBlank
            @Size(min = 5, max = 45)
            String name) {
        return ResponseEntity.ok(this.visitService.getTreatmentsByDoctorName(name));
    }

    @GetMapping("/diagnoses/by-doctor/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'DOCTOR')")
    public ResponseEntity<List<VisitDoctorDiagnosisResponseDTO>> getDiagnosesByDoctorId(@PathVariable @Min(1) long id) {
        return ResponseEntity.ok(this.visitService.getDiagnosesByDoctorId(id));
    }

    @GetMapping("/diagnoses/by-doctor-name")
    @PreAuthorize("hasAnyRole('ADMIN', 'DOCTOR')")
    public ResponseEntity<List<VisitDoctorDiagnosisResponseDTO>> getDiagnosesByDoctorName(
            @RequestParam
            @NotBlank
            @Size(min = 5, max = 45)
            String name) {
        return ResponseEntity.ok(this.visitService.getDiagnosesByDoctorName(name));
    }

    @GetMapping("/by-date-range")
    @PreAuthorize("hasAnyRole('ADMIN', 'DOCTOR')")
    public ResponseEntity<List<VisitResponseDTO>> getVisitsByDateRange(
            @RequestParam
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            @NotNull LocalDate startDate,
            @RequestParam
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            @NotNull LocalDate endDate) {

        return ResponseEntity.ok(this.visitService.getVisitsInPeriod(startDate, endDate));
    }

    @GetMapping("/by-date-range/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'DOCTOR')")
    public ResponseEntity<List<VisitDoctorResponseDTO>> getVisitsByDateRangeAndDoctorId(
            @PathVariable @Min(1) long id,
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            @NotNull LocalDate startDate,
            @RequestParam
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            @NotNull LocalDate endDate) {

        return ResponseEntity.ok(this.visitService.getVisitsInPeriodByDoctorId(id, startDate, endDate));
    }

    @GetMapping("/by-doctor-name")
    @PreAuthorize("hasAnyRole('ADMIN', 'DOCTOR')")
    public ResponseEntity<List<VisitDoctorResponseDTO>> getVisitsByDateRangeAndDoctorName(
            @RequestParam
            @NotBlank
            @Size(min = 5, max = 45)
            String name,
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            @NotNull LocalDate startDate,
            @RequestParam
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            @NotNull LocalDate endDate) {

        return ResponseEntity.ok(this.visitService.getVisitsInPeriodByDoctorName(name, startDate, endDate));
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'DOCTOR')")
    public ResponseEntity<CreateVisitDTO> createVisit(@Valid @RequestBody CreateVisitDTO dto) {
        return ResponseEntity.ok(this.visitService.createVisit(dto));
    }

    @PatchMapping("/treatments/{id}")
    @PreAuthorize("hasRole('ADMIN') or @visitSecurityService.isDoctorOwnerOfVisit(#id)")
    public ResponseEntity<UpdateVisitTreatmentDTO> updateVisit(@PathVariable @Min(1) long id, @Valid @RequestBody UpdateVisitTreatmentDTO dto) {
        return ResponseEntity.ok(this.visitService.updateVisit(id, dto));
    }

    @PatchMapping("/diagnoses/{id}")
    @PreAuthorize("hasRole('ADMIN') or @visitSecurityService.isDoctorOwnerOfVisit(#id)")
    public ResponseEntity<UpdateVisitDiagnosisDTO> updateVisit(@PathVariable @Min(1) long id, @Valid @RequestBody UpdateVisitDiagnosisDTO dto) {
        return ResponseEntity.ok(this.visitService.updateVisit(id, dto));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteVisit(@PathVariable @Min(1) long id) {
        this.visitService.deleteVisit(id);
    }
}
