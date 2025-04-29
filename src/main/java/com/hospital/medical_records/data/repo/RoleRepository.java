package com.hospital.medical_records.data.repo;

import com.hospital.medical_records.data.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByAuthority(String name);
}
