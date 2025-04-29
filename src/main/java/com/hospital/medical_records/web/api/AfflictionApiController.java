package com.hospital.medical_records.web.api;

import com.hospital.medical_records.dto.affliction.AfflictionCountResponseDTO;
import com.hospital.medical_records.dto.affliction.AfflictionResponseDTO;
import com.hospital.medical_records.dto.affliction.CreateAfflictionDTO;
import com.hospital.medical_records.dto.affliction.PatchAfflictionDTO;
import com.hospital.medical_records.service.AfflictionService;
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
@RequestMapping("/api/afflictions")
@Validated
public class AfflictionApiController {
    private final AfflictionService afflictionService;

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'DOCTOR')")
    public ResponseEntity<List<AfflictionResponseDTO>> getAfflictions() {
        List<AfflictionResponseDTO> afflictions = afflictionService.getAfflictions();
        return ResponseEntity.ok(afflictions);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'DOCTOR')")
    public ResponseEntity<AfflictionResponseDTO> getAfflictionById(@PathVariable @Min(1) long id) {
        AfflictionResponseDTO affliction = afflictionService.getAfflictionById(id);
        return ResponseEntity.ok(affliction);
    }

    @GetMapping("/by-count")
    @PreAuthorize("hasAnyRole('ADMIN', 'DOCTOR')")
    public ResponseEntity<List<AfflictionCountResponseDTO>> getAfflictionsByCount() {1
        return ResponseEntity.ok(afflictionService.getAfflictionsByCounts());
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CreateAfflictionDTO> createAffliction(@Valid @RequestBody CreateAfflictionDTO dto) {
        return ResponseEntity.ok(this.afflictionService.createAffliction(dto));
    }

    @PatchMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<PatchAfflictionDTO> updateAffliction(@PathVariable @Min(1) long id, @Valid @RequestBody PatchAfflictionDTO dto) {
        this.afflictionService.updateAffliction(id, dto);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteAffliction(@PathVariable @Min(1) long id) {
        this.afflictionService.deleteAffliction(id);
    }
}
