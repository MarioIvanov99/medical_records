package com.hospital.medical_records.web.api;

import com.hospital.medical_records.dto.sickLeaveRecord.*;
import com.hospital.medical_records.service.SickLeaveRecordService;
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
@RequestMapping("/api/sickleaverecords")
@Validated
public class SickLeaveRecordApiController {
    private final SickLeaveRecordService sickLeaveRecordService;

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'DOCTOR')")
    public ResponseEntity<List<SickLeaveRecordResponseDTO>> getSickLeaveRecords() {
        return ResponseEntity.ok(this.sickLeaveRecordService.getSickLeaveRecords());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'DOCTOR')")
    public ResponseEntity<SickLeaveRecordResponseDTO> getSickLeaveRecordById(@PathVariable @Min(1) long id) {
        return ResponseEntity.ok(this.sickLeaveRecordService.getSickLeaveRecordById(id));
    }

    @GetMapping("/by-patient/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'DOCTOR') or (isAuthenticated() and #id == authentication.principal.getId())")
    public ResponseEntity<List<SickLeaveRecordPatientResponseDTO>> getSickLeaveRecordsByPatientId(@PathVariable @Min(1) long id) {
        return ResponseEntity.ok(this.sickLeaveRecordService.getSickLeaveRecordsByPatientId(id));
    }

    @GetMapping("/by-doctor/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'DOCTOR')")
    public ResponseEntity<List<SickLeaveRecordDoctorResponseDTO>> getSickLeaveRecordsByDoctorId(@PathVariable @Min(1) long id) {
        return ResponseEntity.ok(this.sickLeaveRecordService.getSickLeaveRecordsByDoctorId(id));
    }

    @GetMapping("/by-doctor-name")
    @PreAuthorize("hasAnyRole('ADMIN', 'DOCTOR')")
    public ResponseEntity<List<SickLeaveRecordDoctorResponseDTO>> getSickLeaveRecordsByDoctorName(
            @RequestParam
            @NotBlank
            @Size(min = 5, max = 45)
            String name) {
        return ResponseEntity.ok(this.sickLeaveRecordService.getSickLeaveRecordsByDoctorName(name));
    }

    @GetMapping("/by-month-count")
    @PreAuthorize("hasAnyRole('ADMIN', 'DOCTOR')")
    public ResponseEntity<List<SickLeaveRecordMonthCountDTO>> getMonthWithHighestSickLeaves() {
        return ResponseEntity.ok(this.sickLeaveRecordService.getSickLeaveRecordMonthCount());
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CreateSickLeaveRecordDTO> createSickLeaveRecord(@Valid @RequestBody CreateSickLeaveRecordDTO dto) {
        return ResponseEntity.ok(this.sickLeaveRecordService.createSickLeaveRecord(dto));
    }
    
    @PatchMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UpdateSickLeaveRecordDTO> updateSickLeaveRecord(@PathVariable @Min(1) long id, @Valid @RequestBody UpdateSickLeaveRecordDTO dto) {
        return ResponseEntity.ok(this.sickLeaveRecordService.updateSickLeaveRecord(id, dto));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteSickLeaveRecord(@PathVariable @Min(1) long id) {
        this.sickLeaveRecordService.deleteSickLeaveRecord(id);
    }
}
