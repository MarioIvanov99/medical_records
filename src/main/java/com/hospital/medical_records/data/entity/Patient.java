package com.hospital.medical_records.data.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "patients")
public class Patient extends User {
    @Column(nullable = false, unique = true)
    @NotBlank
    @Size(min = 10, max = 10)
    @Pattern(regexp = "\\d{10}", message = "Personal id must be exactly 10 digits")
    private String personalId;

    @Column
    @FutureOrPresent
    private LocalDate insuranceValidUntilDate;

    @ManyToOne(fetch = FetchType.LAZY)
    private Doctor gp;

    @OneToMany(mappedBy = "patient")
    private Set<HealthInsurancePayment> healthInsurancePayments;

    @OneToMany(mappedBy = "patient")
    private Set<Visit> visits;
}
