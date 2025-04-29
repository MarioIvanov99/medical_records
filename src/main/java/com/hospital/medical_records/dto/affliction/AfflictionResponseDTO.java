package com.hospital.medical_records.dto.affliction;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class AfflictionResponseDTO {
    private long id;

    @NotBlank
    @Size(min = 3, max = 10)
    private String code;

    @NotBlank
    @Size(min = 5, max = 20)
    private String name;
}
