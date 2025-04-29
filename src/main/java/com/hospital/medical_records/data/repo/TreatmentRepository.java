package com.hospital.medical_records.data.repo;

import com.hospital.medical_records.data.entity.Treatment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TreatmentRepository extends JpaRepository<Treatment, Long> {
}
