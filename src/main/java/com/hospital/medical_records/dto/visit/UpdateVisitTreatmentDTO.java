package com.hospital.medical_records.dto.visit;

import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class UpdateVisitTreatmentDTO {
    @NotNull
    private Set<Long> treatmentIds;
}
