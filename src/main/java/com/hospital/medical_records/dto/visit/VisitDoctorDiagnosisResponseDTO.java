package com.hospital.medical_records.dto.visit;

import com.hospital.medical_records.dto.diagnosis.DiagnosisResponseDTO;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDate;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class VisitDoctorDiagnosisResponseDTO {
    @NotBlank
    @Size(min = 5, max = 45)
    private String patientName;

    @PastOrPresent
    private LocalDate visitDate;

    @NotEmpty
    private Set<DiagnosisResponseDTO> diagnoses;
}
