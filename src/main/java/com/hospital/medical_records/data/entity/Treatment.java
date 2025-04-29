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
@Table(name = "treatments")
public class Treatment extends BaseEntity {
    @Column(nullable = false)
    @NotBlank
    @Size(min = 5, max = 50)
    private String name;

    @Column(columnDefinition = "TEXT")
    @NotBlank
    @Size(min = 20, max = 1000)
    private String description;

    @ManyToMany(mappedBy = "treatments")
    private Set<Visit> visits;
}
