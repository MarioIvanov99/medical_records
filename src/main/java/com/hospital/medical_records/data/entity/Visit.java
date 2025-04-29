package com.hospital.medical_records.data.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.PastOrPresent;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "medical_visits")
public class Visit extends BaseEntity {
    @ManyToOne(fetch = FetchType.LAZY)
    private Patient patient;

    @ManyToOne(fetch = FetchType.LAZY)
    private Doctor doctor;

    @PastOrPresent
    private LocalDate visitDate;

    @OneToOne(mappedBy = "visit", fetch = FetchType.LAZY)
    private SickLeaveRecord sickLeaveRecord;

    @ManyToMany
    private Set<Treatment> treatments;

    @ManyToMany
    private Set<Diagnosis> diagnoses;
}
