package com.hospital.medical_records.dto.specialty;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class SpecialtyDTO {
    @NotBlank
    @Size(min = 5, max = 20)
    private String name;
}