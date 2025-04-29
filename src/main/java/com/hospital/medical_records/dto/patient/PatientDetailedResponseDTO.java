package com.hospital.medical_records.dto.patient;

import jakarta.persistence.Column;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class PatientDetailedResponseDTO {
    private long id;

    @NotBlank
    @Size(min = 5, max = 45)
    private String name;

    @NotBlank
    private String password;

    @NotBlank
    @Size(min = 10, max = 10)
    @Pattern(regexp = "\\d{10}", message = "Personal id must be exactly 10 digits")
    private String personalId;

    @FutureOrPresent
    private LocalDate insuranceValidUntilDate;

    @Size(min = 5, max = 45)
    private String gpName;
}
