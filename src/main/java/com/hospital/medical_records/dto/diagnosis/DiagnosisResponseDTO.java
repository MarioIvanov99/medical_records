package com.hospital.medical_records.dto.diagnosis;

import com.hospital.medical_records.dto.affliction.AfflictionResponseDTO;
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
public class DiagnosisResponseDTO {
    @NotBlank
    @Size(min = 20, max = 1000)
    private String description;
    private Set<AfflictionResponseDTO> afflictions;
}
