package com.hospital.medical_records.dto.doctor;

import com.hospital.medical_records.dto.specialty.SpecialtyDTO;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class DoctorResponseDTO {
    private long id;

    @NotBlank
    @Size(min = 5, max = 45)
    private String name;

    private boolean gp;
    private Set<SpecialtyDTO> specialties;
}
