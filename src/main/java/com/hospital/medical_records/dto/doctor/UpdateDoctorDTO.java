package com.hospital.medical_records.dto.doctor;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class UpdateDoctorDTO {
    @Size(min = 5, max = 45)
    private String name;

    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$",
            message = "Password must be at least 8 characters with at least one letter and one number")
    private String password;

    @NotBlank
    @Size(min = 5, max = 45)
    private String username;

    private boolean gp;
    private Set<Long> specialtyIds;
}