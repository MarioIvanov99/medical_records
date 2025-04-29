package com.hospital.medical_records.dto.visit;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.*;

import java.time.LocalDate;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class CreateVisitDTO {
    @NotNull
    private Long patientId;

    @NotNull
    private Long doctorId;

    @NotNull
    @PastOrPresent
    private LocalDate visitDate;

    private Set<Long> treatmentIds;
    private Set<Long> diagnosisIds;
}
