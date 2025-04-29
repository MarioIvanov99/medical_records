package com.hospital.medical_records.data.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.PastOrPresent;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@Table(name = "health_insurance_payments")
public class HealthInsurancePayment extends BaseEntity {
    @Column(nullable = false)
    @PastOrPresent
    private LocalDate paymentDate;

    @ManyToOne(fetch = FetchType.LAZY)
    private Patient patient;
}
