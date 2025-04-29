package com.hospital.medical_records.dto.visit;

import com.hospital.medical_records.dto.diagnosis.DiagnosisResponseDTO;
import com.hospital.medical_records.dto.treatment.TreatmentResponseDTO;
import lombok.*;
import jakarta.validation.constraints.*;
import java.time.LocalDate;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class VisitResponseDTO {
    @NotBlank
    @Size(min = 5, max = 45)
    private String patientName;

    @NotBlank
    @Size(min = 5, max = 45)
    private String doctorName;

    @PastOrPresent
    private LocalDate visitDate;

    private Long sickLeaveRecordId;

    private Set<TreatmentResponseDTO> treatments;
    private Set<DiagnosisResponseDTO> diagnoses;
}
