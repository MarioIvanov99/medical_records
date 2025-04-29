package com.hospital.medical_records.web.api;

import com.hospital.medical_records.dto.healthInsurancePayment.CreateHealthInsurancePaymentDTO;
import com.hospital.medical_records.dto.healthInsurancePayment.HealthInsurancePaymentDetailedDTO;
import com.hospital.medical_records.dto.healthInsurancePayment.HealthInsurancePaymentResponseDTO;
import com.hospital.medical_records.dto.healthInsurancePayment.UpdateHealthInsurancePaymentDTO;
import com.hospital.medical_records.service.HealthInsurancePaymentService;
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
@RequestMapping("/api/payments")
@Validated
public class HealthInsurancePaymentApiController {
    private final HealthInsurancePaymentService healthInsurancePaymentService;

    @GetMapping
    @PreAuthorize("hasRole('ADMIN') or hasRole('DOCTOR')")
    public ResponseEntity<List<HealthInsurancePaymentResponseDTO>> getHealthInsurancePayments() {
        return ResponseEntity.ok(healthInsurancePaymentService.getHealthInsurancePayments());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('DOCTOR')")
    public ResponseEntity<HealthInsurancePaymentResponseDTO> getHealthInsurancePaymentById(@PathVariable @Min(1) long id) {
        return ResponseEntity.ok(healthInsurancePaymentService.getHealthInsurancePaymentById(id));
    }

    @GetMapping("/detailed")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<HealthInsurancePaymentDetailedDTO>> getDetailedHealthInsurancePayments() {
        return ResponseEntity.ok(healthInsurancePaymentService.getDetailedHealthInsurancePayments());
    }

    @GetMapping("/detailed/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<HealthInsurancePaymentDetailedDTO> getDetailedHealthInsurancePaymentById(@PathVariable @Min(1) long id) {
        return ResponseEntity.ok(healthInsurancePaymentService.getDetailedHealthInsurancePaymentById(id));
    }

    @GetMapping("/by-patient/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'DOCTOR') or (isAuthenticated() and #id == authentication.principal.getId())")
    public ResponseEntity<List<HealthInsurancePaymentResponseDTO>> getHealthInsurancePaymentByPatientId(@PathVariable @Min(1) long id) {
        return ResponseEntity.ok(healthInsurancePaymentService.getHealthInsurancePaymentsByPatientId(id));
    }

    @GetMapping("/detailed/by-patient/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<HealthInsurancePaymentDetailedDTO>> getDetailedHealthInsurancePaymentByPatientId(@PathVariable @Min(1) long id) {
        return ResponseEntity.ok(healthInsurancePaymentService.getDetailedHealthInsurancePaymentsByPatientId(id));
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CreateHealthInsurancePaymentDTO> createHealthInsurancePayment(@Valid @RequestBody CreateHealthInsurancePaymentDTO dto) {
        return ResponseEntity.ok(healthInsurancePaymentService.createHealthInsurancePayment(dto));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UpdateHealthInsurancePaymentDTO> updateHealthInsurancePayment(@PathVariable @Min(1) long id, @Valid @RequestBody UpdateHealthInsurancePaymentDTO dto) {
        healthInsurancePaymentService.updateHealthInsurancePayment(id, dto);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteHealthInsurancePayment(@PathVariable @Min(1) long id) {
        healthInsurancePaymentService.deleteHealthInsurancePayment(id);
    }
}
