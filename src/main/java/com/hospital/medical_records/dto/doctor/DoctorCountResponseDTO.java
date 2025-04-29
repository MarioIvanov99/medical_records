package com.hospital.medical_records.dto.doctor;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class DoctorCountResponseDTO {
    @NotBlank
    @Size(min = 5, max = 45)
    private String doctorName;

    private long count;
}
