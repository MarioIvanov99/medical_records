package com.hospital.medical_records.data.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "afflictions")
public class Affliction extends BaseEntity {

    @Column(nullable = false)
    @NotBlank
    @Size(min = 3, max = 10)
    private String code;

    @Column(nullable = false)
    @NotBlank
    @Size(min = 5, max = 20)
    private String name;

    @ManyToMany(mappedBy = "afflictions")
    private Set<Diagnosis> diagnoses;
}
