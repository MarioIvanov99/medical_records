package com.hospital.medical_records.data.repo;

import com.hospital.medical_records.data.entity.Specialty;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpecialtyRepository extends JpaRepository<Specialty, Long> {
}
