package com.hospital.medical_records.dto.healthInsurancePayment;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class HealthInsurancePaymentDetailedDTO {
    private long id;
    @PastOrPresent
    private LocalDate paymentDate;

    @NotBlank
    @Size(min = 5, max = 45)
    private String patientName;
}
