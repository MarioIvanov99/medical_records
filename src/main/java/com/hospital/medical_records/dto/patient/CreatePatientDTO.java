package com.hospital.medical_records.dto.patient;

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
public class CreatePatientDTO {
    @NotBlank
    @Size(min = 5, max = 45)
    private String name;

    @NotBlank
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$",
            message = "Password must be at least 8 characters with at least one letter and one number")
    private String password;

    @NotBlank
    @Size(min = 10, max = 10)
    @Pattern(regexp = "\\d{10}", message = "Personal id must be exactly 10 digits")
    private String personalId;

    @NotBlank
    @Size(min = 5, max = 45)
    private String username;

    @FutureOrPresent
    private LocalDate insuranceValidUntilDate;

    private long gpId;
}
