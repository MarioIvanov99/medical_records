package com.hospital.medical_records.data.mapper;

import com.hospital.medical_records.data.entity.HealthInsurancePayment;
import com.hospital.medical_records.dto.healthInsurancePayment.CreateHealthInsurancePaymentDTO;
import com.hospital.medical_records.dto.healthInsurancePayment.HealthInsurancePaymentDetailedDTO;
import com.hospital.medical_records.dto.healthInsurancePayment.HealthInsurancePaymentResponseDTO;
import com.hospital.medical_records.dto.healthInsurancePayment.UpdateHealthInsurancePaymentDTO;
import com.hospital.medical_records.service.PatientService;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", uses = {PatientService.class})
public interface HealthInsurancePaymentMapper {
    @Mapping(source = "patientId", target = "patient")
    HealthInsurancePayment toEntity(CreateHealthInsurancePaymentDTO createHealthInsurancePaymentDTO);

    HealthInsurancePaymentResponseDTO toResponseDto(HealthInsurancePayment healthInsurancePayment);

    @Mapping(source = "patient.name", target = "patientName")
    HealthInsurancePaymentDetailedDTO toDetailedResponseDto(HealthInsurancePayment healthInsurancePayment);

    List<HealthInsurancePaymentResponseDTO> toResponseDtoList(List<HealthInsurancePayment> healthInsurancePayments);
    List<HealthInsurancePaymentDetailedDTO> toDetailedResponseDtoList(List<HealthInsurancePayment> healthInsurancePayments);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateHealthInsurancePayment(UpdateHealthInsurancePaymentDTO dto, @MappingTarget HealthInsurancePayment healthInsurancePayment);
}
