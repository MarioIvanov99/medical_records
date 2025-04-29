package com.hospital.medical_records.web.api;

import com.hospital.medical_records.data.entity.Treatment;
import com.hospital.medical_records.dto.treatment.TreatmentResponseDTO;
import com.hospital.medical_records.dto.treatment.CreateTreatmentDTO;
import com.hospital.medical_records.dto.treatment.UpdateTreatmentDTO;
import com.hospital.medical_records.service.TreatmentService;
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
@RequestMapping("/api/treatments")
@Validated
public class TreatmentApiController {
    private final TreatmentService treatmentService;

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'DOCTOR')")
    public ResponseEntity<List<TreatmentResponseDTO>> getTreatments() {
        List<TreatmentResponseDTO> treatments = treatmentService.getTreatments();
        return ResponseEntity.ok(treatments);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'DOCTOR')")
    public ResponseEntity<TreatmentResponseDTO> getTreatmentById(@PathVariable @Min(1) long id) {
        TreatmentResponseDTO treatment = treatmentService.getTreatmentById(id);
        return ResponseEntity.ok(treatment);
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CreateTreatmentDTO> createTreatment(@Valid @RequestBody CreateTreatmentDTO dto) {
        return ResponseEntity.ok(this.treatmentService.createTreatment(dto));
    }

    @PatchMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UpdateTreatmentDTO> updateTreatment(@PathVariable @Min(1) long id, @Valid @RequestBody UpdateTreatmentDTO dto) {
        this.treatmentService.updateTreatment(id, dto);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteTreatment(@PathVariable @Min(1) long id) {
        this.treatmentService.deleteTreatment(id);
    }
}
