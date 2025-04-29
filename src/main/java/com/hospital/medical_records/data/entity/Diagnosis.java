package com.hospital.medical_records.data.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "diagnoses")
public class Diagnosis extends BaseEntity {

    @Column(nullable = false, columnDefinition = "TEXT")
    @NotBlank
    @Size(min = 20, max = 1000)
    private String description;

    @ManyToMany(mappedBy = "diagnoses")
    private Set<Visit> visits;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "diagnoses_afflictions",
            joinColumns = @JoinColumn(name = "diagnoses_id"),
            inverseJoinColumns = @JoinColumn(name = "afflictions_id")
    )
    private Set<Affliction> afflictions;
}
