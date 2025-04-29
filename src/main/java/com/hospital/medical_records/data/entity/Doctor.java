package com.hospital.medical_records.data.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "doctors")
public class Doctor extends User {
    @Column(name = "is_gp")
    private boolean gp;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "doctors_specialties",
            joinColumns = @JoinColumn(name = "doctors_id"),
            inverseJoinColumns = @JoinColumn(name = "specialties_id")
    )
    private Set<Specialty> specialties;

    @OneToMany(mappedBy = "gp")
    private Set<Patient> patients;

    @OneToMany(mappedBy = "doctor")
    private Set<Visit> visits;
}
