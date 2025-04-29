package com.hospital.medical_records.service.impl;

import com.hospital.medical_records.data.entity.Role;
import com.hospital.medical_records.data.repo.RoleRepository;
import com.hospital.medical_records.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;

    @Override
    public Role getRoleByName(String name) {
        return roleRepository.findByAuthority(name);
    }
}
