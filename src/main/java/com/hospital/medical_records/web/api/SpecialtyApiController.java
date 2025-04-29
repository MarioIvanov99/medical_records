package com.hospital.medical_records.web.api;

import com.hospital.medical_records.data.entity.Specialty;
import com.hospital.medical_records.dto.specialty.SpecialtyDTO;
import com.hospital.medical_records.service.SpecialtyService;
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
@RequestMapping("/api/specialties")
@Validated
public class SpecialtyApiController {
    private final SpecialtyService specialtyService;

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'DOCTOR')")
    public ResponseEntity<List<SpecialtyDTO>> getSpecialties() {
        return ResponseEntity.ok(this.specialtyService.getSpecialties());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'DOCTOR')")
    public ResponseEntity<SpecialtyDTO> getSpecialtyById(@PathVariable @Min(1) long id) {
        return ResponseEntity.ok(this.specialtyService.getSpecialtyById(id));
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<SpecialtyDTO> createSpecialty(@Valid @RequestBody SpecialtyDTO dto) {
        return ResponseEntity.ok(this.specialtyService.createSpecialty(dto));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<SpecialtyDTO> updateSpecialty(@PathVariable @Min(1) long id, @Valid@RequestBody SpecialtyDTO dto) {
        this.specialtyService.updateSpecialty(id, dto);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteSpecialty(@PathVariable @Min(1) long id) {
        this.specialtyService.deleteSpecialty(id);
    }
}
