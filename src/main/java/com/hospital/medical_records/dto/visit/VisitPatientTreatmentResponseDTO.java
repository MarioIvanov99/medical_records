package com.hospital.medical_records.dto.visit;

import com.hospital.medical_records.dto.treatment.TreatmentResponseDTO;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class VisitPatientTreatmentResponseDTO {
    @NotBlank
    @Size(min = 5, max = 45)
    private String doctorName;

    @PastOrPresent
    private LocalDate visitDate;

    @NotEmpty
    private Set<TreatmentResponseDTO> treatments;
}
