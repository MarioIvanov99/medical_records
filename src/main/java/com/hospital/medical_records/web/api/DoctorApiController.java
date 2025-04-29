package com.hospital.medical_records.web.api;

import com.hospital.medical_records.dto.doctor.CreateDoctorDTO;
import com.hospital.medical_records.dto.doctor.DoctorCountResponseDTO;
import com.hospital.medical_records.dto.doctor.DoctorResponseDTO;
import com.hospital.medical_records.dto.doctor.UpdateDoctorDTO;
import com.hospital.medical_records.service.DoctorService;
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
@RequestMapping("/api/doctors")
@Validated
public class DoctorApiController {
    private final DoctorService doctorService;

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'DOCTOR')")
    public ResponseEntity<List<DoctorResponseDTO>> getDoctors() {
        return ResponseEntity.ok(this.doctorService.getDoctors());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'DOCTOR')")
    public ResponseEntity<DoctorResponseDTO> getDoctorById(@PathVariable @Min(1) long id) {
        return ResponseEntity.ok(this.doctorService.getDoctorById(id));
    }

    @GetMapping("/by-patient-count")
    @PreAuthorize("hasAnyRole('ADMIN', 'DOCTOR')")
    public ResponseEntity<List<DoctorCountResponseDTO>> getGpsByPatientCount() {
        return ResponseEntity.ok(this.doctorService.getGpsByPatientCount());
    }

    @GetMapping("/by-visit-count")
    @PreAuthorize("hasAnyRole('ADMIN', 'DOCTOR')")
    public ResponseEntity<List<DoctorCountResponseDTO>> getDoctorsByVisitCount() {
        return ResponseEntity.ok(this.doctorService.getDoctorsByVisitCount());
    }

    @GetMapping("/most-sick-leaves")
    @PreAuthorize("hasAnyRole('ADMIN', 'DOCTOR')")
    public ResponseEntity<List<DoctorCountResponseDTO>> getMostSickLeaves() {
        return ResponseEntity.ok(this.doctorService.getDoctorsWithMostSicKLeaveRecords());
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CreateDoctorDTO> createDoctor(@Valid @RequestBody CreateDoctorDTO dto) {
        return ResponseEntity.ok(this.doctorService.createDoctor(dto));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UpdateDoctorDTO> updateDoctor(@PathVariable @Min(1) long id, @Valid @RequestBody UpdateDoctorDTO dto) {
        return ResponseEntity.ok(this.doctorService.updateDoctor(id, dto));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteDoctor(@PathVariable @Min(1) long id) {
        this.doctorService.deleteDoctor(id);
    }
}
