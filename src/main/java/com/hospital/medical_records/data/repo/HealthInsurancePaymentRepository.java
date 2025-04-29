package com.hospital.medical_records.data.repo;


import com.hospital.medical_records.data.entity.HealthInsurancePayment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HealthInsurancePaymentRepository extends JpaRepository<HealthInsurancePayment, Long> {
    List<HealthInsurancePayment> findByPatient_IdOrderByPaymentDateDesc(Long patientId);
}
