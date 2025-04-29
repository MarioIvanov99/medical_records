package com.hospital.medical_records.service.impl;

import com.hospital.medical_records.data.entity.HealthInsurancePayment;
import com.hospital.medical_records.data.mapper.HealthInsurancePaymentMapper;
import com.hospital.medical_records.data.repo.HealthInsurancePaymentRepository;
import com.hospital.medical_records.dto.healthInsurancePayment.CreateHealthInsurancePaymentDTO;
import com.hospital.medical_records.dto.healthInsurancePayment.HealthInsurancePaymentDetailedDTO;
import com.hospital.medical_records.dto.healthInsurancePayment.HealthInsurancePaymentResponseDTO;
import com.hospital.medical_records.dto.healthInsurancePayment.UpdateHealthInsurancePaymentDTO;
import com.hospital.medical_records.exception.HealthInsurancePaymentNotFoundException;
import com.hospital.medical_records.service.HealthInsurancePaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HealthInsurancePaymentServiceImpl implements HealthInsurancePaymentService {
    private final HealthInsurancePaymentRepository healthInsurancePaymentRepository;
    private final HealthInsurancePaymentMapper healthInsurancePaymentMapper;

    @Override
    @PreAuthorize("hasAnyRole('ADMIN', 'DOCTOR')")
    public List<HealthInsurancePaymentResponseDTO> getHealthInsurancePayments(){
        return healthInsurancePaymentMapper.toResponseDtoList(healthInsurancePaymentRepository.findAll());
    }
    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public List<HealthInsurancePaymentDetailedDTO> getDetailedHealthInsurancePayments(){
        return healthInsurancePaymentMapper.toDetailedResponseDtoList(healthInsurancePaymentRepository.findAll());
    }
    @Override
    @PreAuthorize("hasAnyRole('ADMIN', 'DOCTOR')")
    public HealthInsurancePaymentResponseDTO getHealthInsurancePaymentById(long id){
        return healthInsurancePaymentMapper.toResponseDto(healthInsurancePaymentRepository.findById(id)
                .orElseThrow(() -> new HealthInsurancePaymentNotFoundException("Health Insurance Payment not found", id))
        );
    }
    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public HealthInsurancePaymentDetailedDTO getDetailedHealthInsurancePaymentById(long id){
        return healthInsurancePaymentMapper.toDetailedResponseDto(healthInsurancePaymentRepository.findById(id)
                .orElseThrow(() -> new HealthInsurancePaymentNotFoundException("Health Insurance Payment not found", id))
        );
    }

    @Override
    @PreAuthorize("hasAnyRole('ADMIN', 'DOCTOR') or (isAuthenticated() and #id == authentication.principal.getId())")
    public List<HealthInsurancePaymentResponseDTO> getHealthInsurancePaymentsByPatientId(long patientId){
        return healthInsurancePaymentMapper.toResponseDtoList(healthInsurancePaymentRepository.findByPatient_IdOrderByPaymentDateDesc(patientId));
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public List<HealthInsurancePaymentDetailedDTO> getDetailedHealthInsurancePaymentsByPatientId(long patientId){
        return healthInsurancePaymentMapper.toDetailedResponseDtoList(healthInsurancePaymentRepository.findByPatient_IdOrderByPaymentDateDesc(patientId));
    }

    @Override
    public HealthInsurancePayment getHealthInsurancePaymentEntityById(long id){
        return healthInsurancePaymentRepository.findById(id)
                .orElseThrow(() -> new HealthInsurancePaymentNotFoundException("Health Insurance Payment not found", id));
    }
    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public CreateHealthInsurancePaymentDTO createHealthInsurancePayment(CreateHealthInsurancePaymentDTO dto){
        HealthInsurancePayment healthInsurancePayment = healthInsurancePaymentMapper.toEntity(dto);
        HealthInsurancePayment saved = healthInsurancePaymentRepository.save(healthInsurancePayment);
        return dto;
    }
    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public HealthInsurancePayment updateHealthInsurancePayment(long id, UpdateHealthInsurancePaymentDTO dto){
        HealthInsurancePayment healthInsurancePayment = healthInsurancePaymentRepository.findById(id)
                .orElseThrow(() -> new HealthInsurancePaymentNotFoundException("Health Insurance Payment not found", id));
        healthInsurancePaymentMapper.updateHealthInsurancePayment(dto, healthInsurancePayment);
        return healthInsurancePaymentRepository.save(healthInsurancePayment);
    }
    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteHealthInsurancePayment(long id){
        if(!healthInsurancePaymentRepository.existsById(id)){
            throw new HealthInsurancePaymentNotFoundException("Health Insurance Payment not found", id);
        }
        healthInsurancePaymentRepository.deleteById(id);
    }
}
