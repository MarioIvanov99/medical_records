package com.hospital.medical_records.dto.diagnosis;

import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class UpdateDiagnosisDTO {
    @Size(min = 20, max = 1000)
    private String description;
    private Set<Long> afflictionIds;
}
