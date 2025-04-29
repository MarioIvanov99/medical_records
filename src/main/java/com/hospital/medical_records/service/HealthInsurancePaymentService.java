package com.hospital.medical_records.service;

import com.hospital.medical_records.data.entity.HealthInsurancePayment;
import com.hospital.medical_records.dto.healthInsurancePayment.CreateHealthInsurancePaymentDTO;
import com.hospital.medical_records.dto.healthInsurancePayment.HealthInsurancePaymentDetailedDTO;
import com.hospital.medical_records.dto.healthInsurancePayment.HealthInsurancePaymentResponseDTO;
import com.hospital.medical_records.dto.healthInsurancePayment.UpdateHealthInsurancePaymentDTO;

import java.util.List;

public interface HealthInsurancePaymentService {
    List<HealthInsurancePaymentResponseDTO> getHealthInsurancePayments();
    List<HealthInsurancePaymentDetailedDTO> getDetailedHealthInsurancePayments();
    HealthInsurancePaymentResponseDTO getHealthInsurancePaymentById(long id);
    HealthInsurancePaymentDetailedDTO getDetailedHealthInsurancePaymentById(long id);
    List<HealthInsurancePaymentResponseDTO> getHealthInsurancePaymentsByPatientId(long id);
    List<HealthInsurancePaymentDetailedDTO> getDetailedHealthInsurancePaymentsByPatientId(long id);
    HealthInsurancePayment getHealthInsurancePaymentEntityById(long id);
    CreateHealthInsurancePaymentDTO createHealthInsurancePayment(CreateHealthInsurancePaymentDTO dto);
    HealthInsurancePayment updateHealthInsurancePayment(long id, UpdateHealthInsurancePaymentDTO dto);
    void deleteHealthInsurancePayment(long id);
}
