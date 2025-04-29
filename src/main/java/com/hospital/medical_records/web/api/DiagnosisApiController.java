package com.hospital.medical_records.web.api;

import com.hospital.medical_records.dto.diagnosis.CreateDiagnosisDTO;
import com.hospital.medical_records.dto.diagnosis.DiagnosisResponseDTO;
import com.hospital.medical_records.dto.diagnosis.UpdateDiagnosisDTO;
import com.hospital.medical_records.service.DiagnosisService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/diagnoses")
@Validated
public class DiagnosisApiController {
    private final DiagnosisService diagnosisService;

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'DOCTOR')")
    public ResponseEntity<List<DiagnosisResponseDTO>> getDiagnoses() {
        return ResponseEntity.ok(this.diagnosisService.getDiagnoses());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'DOCTOR')")
    public ResponseEntity<DiagnosisResponseDTO> getDiagnosisById(@PathVariable @Min(1) long id) {
        return ResponseEntity.ok(this.diagnosisService.getDiagnosisById(id));
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CreateDiagnosisDTO> createDiagnosis(@Valid @RequestBody CreateDiagnosisDTO dto) {
        return ResponseEntity.ok(this.diagnosisService.createDiagnosis(dto));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UpdateDiagnosisDTO> updateDiagnosis(@PathVariable @Min(1) long id, @Valid @RequestBody UpdateDiagnosisDTO dto) {
        return ResponseEntity.ok(this.diagnosisService.updateDiagnosis(id, dto));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteDiagnosis(@PathVariable @Min(1) long id) {
        this.diagnosisService.deleteDiagnosis(id);
    }
}
