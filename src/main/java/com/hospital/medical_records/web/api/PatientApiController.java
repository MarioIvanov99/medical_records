package com.hospital.medical_records.web.api;

import com.hospital.medical_records.dto.patient.*;
import com.hospital.medical_records.service.PatientService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/patients")
@Validated
public class PatientApiController {
    private final PatientService patientService;

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'DOCTOR')")
    public ResponseEntity<List<PatientResponseDTO>> getPatients() {
        return ResponseEntity.ok(patientService.getPatients());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'DOCTOR')")
    public ResponseEntity<PatientResponseDTO> getPatientById(@PathVariable @Min(1) long id) {
        return ResponseEntity.ok(patientService.getPatientById(id));
    }

    @GetMapping("/detailed")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<PatientDetailedResponseDTO>> getDetailedPatients() {
        return ResponseEntity.ok(patientService.getDetailedPatients());
    }

    @GetMapping("/detailed/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<PatientDetailedResponseDTO> getDetailedPatientById(@PathVariable @Min(1) long id) {
        return ResponseEntity.ok(patientService.getDetailedPatientById(id));
    }

    @GetMapping("/by-diagnosis/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'DOCTOR')")
    public ResponseEntity<List<PatientNameCodeDTO>> getPatientsByDiagnosis(@PathVariable @Min(1) long id) {
        return ResponseEntity.ok(patientService.getPatientsByDiagnosisId(id));
    }

    @GetMapping("/by-affliction-name")
    @PreAuthorize("hasAnyRole('ADMIN', 'DOCTOR')")
    public ResponseEntity<List<PatientNameCodeDTO>> getPatientsByAffliction(
            @RequestParam
            @NotBlank
            @Size(min = 5, max = 20)
            String name) {
        return ResponseEntity.ok(patientService.getPatientsByAfflictionName(name));
    }

    @GetMapping("/by-gp/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'DOCTOR')")
    public ResponseEntity<List<PatientNameCodeDTO>> getPatientsByGp(@PathVariable @Min(1) long id) {
        return ResponseEntity.ok(patientService.getPatientsByGpId(id));
    }

    @GetMapping("/by-gp-name")
    @PreAuthorize("hasAnyRole('ADMIN', 'DOCTOR')")
    public ResponseEntity<List<PatientNameCodeDTO>> getPatientsByGpName(
            @RequestParam
            @NotBlank
            @Size(min = 5, max = 45)
            String name) {
        return ResponseEntity.ok(patientService.getPatientsByGpName(name));
    }


    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CreatePatientDTO> createPatient(@Valid @RequestBody CreatePatientDTO dto) {
        return ResponseEntity.ok(patientService.createPatient(dto));
    }

    @PatchMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UpdatePatientDTO> updatePatient(@PathVariable @Min(1) long id, @Valid @RequestBody UpdatePatientDTO dto) {
        patientService.updatePatient(id, dto);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void deletePatient(@PathVariable @Min(1) long id) {
        patientService.deletePatient(id);
    }
}
