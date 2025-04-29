
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
public class UpdateVisitDiagnosisDTO {
    @NotNull
    private Set<Long> diagnosisIds;
}